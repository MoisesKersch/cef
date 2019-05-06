package com.mk.cef.services;

import com.mk.cef.models.Emprestimo;
import com.mk.cef.models.Parcela;
import com.mk.cef.repositories.EmprestimoRepository;
import com.mk.cef.repositories.ParcelaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {

    private ParcelaRepository parcelaRepository;

    EmprestimoServiceImpl(ParcelaRepository parcelaRepository) {
        this.parcelaRepository = parcelaRepository;
    }

    @Override
    public Object save(List<Parcela> parcelas) {
        try {
            parcelas.forEach(parcela -> {
                this.parcelaRepository.save(parcela);
            });
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
        return true;
    }

    @Override
    public Object gerarDetalhes(Emprestimo emprestimo) {
        try {
            LocalDate localDate = emprestimo.getDataPrimeiraParcela().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            emprestimo.setValorEmprestimo(this.adicionarCincoporcentoNoValor(emprestimo.getValorEmprestimo()));

            BigDecimal valorTotalDividido = emprestimo.getValorEmprestimo().divide( BigDecimal.valueOf(emprestimo.getQuantidadeParcela()), 2, RoundingMode.HALF_UP);
            BigDecimal conferindoDiferenca = valorTotalDividido.multiply( BigDecimal.valueOf( emprestimo.getQuantidadeParcela()).setScale(2, RoundingMode.HALF_UP) ).setScale(2, RoundingMode.HALF_UP);
            BigDecimal diferenca = emprestimo.getValorEmprestimo().subtract(conferindoDiferenca).setScale(2, RoundingMode.HALF_UP);

            List<Parcela> parcelas = new ArrayList<>();

            for (Integer x = 0; x < emprestimo.getQuantidadeParcela(); x++) {
                Parcela parcela = new Parcela();
                parcela.setNumero(x + 1);
                parcela.setFuncionario(emprestimo.getFuncionario());
                parcela.setValor(valorTotalDividido);
                parcela.setVencimento( Date.valueOf(localDate.plusDays(x * 30)) );
                parcelas.add(parcela);
            }

            if (!diferenca.equals(BigDecimal.ZERO))
                parcelas.get(parcelas.size() - 1).setValor(valorTotalDividido.add(diferenca));

            return parcelas;
        } catch (Exception e) {
            return new ArrayList<Parcela>();
        }
    }

    private BigDecimal adicionarCincoporcentoNoValor(BigDecimal input) {
        try {
            return input.multiply(new BigDecimal("5")).divide(new BigDecimal("100")).add(input);
        }catch (Exception e) {
            return null;
        }
    }
}
