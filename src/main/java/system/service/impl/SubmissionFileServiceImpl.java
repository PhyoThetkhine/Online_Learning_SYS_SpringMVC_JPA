package system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.model.DTO.SubmissionFileDTO;
import system.model.entity.Submission;
import system.model.entity.SubmissionFile;
import system.model.mapper.SubmissionFileMapper;
import system.model.repo.SubmissionFileRepository;
import system.model.repo.SubmissionRepository;
import system.service.SubmissionFileService;
@Service
public class SubmissionFileServiceImpl implements SubmissionFileService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private SubmissionFileRepository submissionFileRepository;

    @Autowired
    private SubmissionFileMapper submissionFileMapper;

    @Override
    public void saveSubmissionFile(SubmissionFileDTO dto) {
        Submission submission = submissionRepository.findById(dto.getSubmissionID());
        dto.setSubmission(submission);
        SubmissionFile submissionFile = submissionFileMapper.toEntity(dto);
        submissionFileRepository.save(submissionFile);
    }

    @Override
    public List<SubmissionFileDTO> getFilesBySubmissionId(Integer submissionId) {
        List<SubmissionFile> submissionFiles = submissionFileRepository.findBySubmissionId(submissionId);
        return submissionFiles.stream()
                              .map(submissionFileMapper::toDTO)
                              .collect(Collectors.toList());
    }
}