package edu.icet.service;

import edu.icet.dto.Paper;

public interface PaperService {
    Boolean createPaper(Paper paper);

    Paper findPaperById(Integer id);

    Boolean updatePaper(Paper paper);

    Boolean deletePaper(Integer id);
}
