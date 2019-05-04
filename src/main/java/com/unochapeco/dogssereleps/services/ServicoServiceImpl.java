package com.unochapeco.dogssereleps.services;

import com.unochapeco.dogssereleps.models.Servico;
import com.unochapeco.dogssereleps.models.User;
import com.unochapeco.dogssereleps.repositories.AnimalRepository;
import com.unochapeco.dogssereleps.repositories.ServicoRepository;
import com.unochapeco.dogssereleps.repositories.TipoServicoRepository;
import com.unochapeco.dogssereleps.utils.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ServicoServiceImpl implements ServicoService {

    private ServicoRepository servicoRepository;

    @Autowired
    private TipoServicoRepository tipoServicoRepository;

    @Autowired
    private AnimalRepository animalRepository;

    ServicoServiceImpl(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Override
    public Servico save(Servico servico) {
        try {
            return servicoRepository.save(servico);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Servico> findByUser(User user) {
        try {
            return this.servicoRepository.findByUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Servico> findByStatusNovo(User user) {
        try {
            double lat = user.getLatitude();
            double lon = user.getLongitude();

            List<Servico> servico = this.servicoRepository.findByStatusAndUserNot("NOVO", user);

            for (Servico s : servico) {
                if ( s.getLatitude() != null )
                    s.setDistancia(DistanceUtil.distance(lat, lon, s.getLatitude(), s.getLongitude(), 'K'));
                else {
                    s.setDistancia(0);
                }
            }
            Collections.sort(servico, new SortbyDistancia());
            return servico;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    class SortbyDistancia implements Comparator<Servico> {
        public int compare(Servico a, Servico b) {
            return Double.compare(a.getDistancia(), b.getDistancia());
        }
    }
}
