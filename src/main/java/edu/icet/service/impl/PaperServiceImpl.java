package edu.icet.service.impl;

import edu.icet.dto.Paper;
import edu.icet.entity.PaperEntity;
import edu.icet.repository.PaperRepository;
import edu.icet.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaperServiceImpl implements PaperService {

    final PaperRepository paperRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean createPaper(Paper paper) {
        try {
            PaperEntity paperEntity = modelMapper.map(paper, PaperEntity.class);
            paperRepository.save(paperEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Paper findPaperById(Integer id) {
        Optional<PaperEntity> optionalPaperEntity = paperRepository.findById(id);
        if (optionalPaperEntity.isPresent()) {
            return modelMapper.map(optionalPaperEntity.get(), Paper.class);
        }
        return null;
    }

    @Override
    public Boolean updatePaper(Paper paper) {
        if (paper.getId() == null) {
            return false;
        }

        Optional<PaperEntity> optionalPaperEntity = paperRepository.findById(paper.getId());
        if (optionalPaperEntity.isPresent()) {
            PaperEntity paperEntity = modelMapper.map(paper, PaperEntity.class);
            paperRepository.save(paperEntity);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deletePaper(Integer id) {
        if (paperRepository.existsById(id)) {
            paperRepository.deleteById(id);
            return true;
        }
        return false;
    }
}