package br.com.textoit.goldenraspberryawards.service;

import br.com.textoit.goldenraspberryawards.domain.Studio;
import br.com.textoit.goldenraspberryawards.domain.repository.StudioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Slf4j
@Service
public class StudioService {

    private final StudioRepository studioRepository;

    public StudioService(final StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    public List<Studio> getStudioByIds(final List<Long> idsStudio) {
        final List<Studio> studiosList = this.studioRepository.findByIds(idsStudio);
        if (studiosList.isEmpty()){
            throw new NotFoundException("Studios not found");
        }
        return studiosList;
    }
}



