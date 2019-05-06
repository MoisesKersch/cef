package com.mk.cef.services;

import com.mk.cef.models.Emprestimo;
import com.mk.cef.models.Parcela;

import java.util.List;

public interface EmprestimoService {
    public Object save(List<Parcela> parcelas);
    public Object gerarDetalhes(Emprestimo emprestimo);
}
