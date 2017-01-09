package ua.lviv.calltech.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.entity.QuestionType;
import ua.lviv.calltech.repository.QuestionTypeRepository;
import ua.lviv.calltech.service.QuestionTypeService;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService{

	@Autowired
	private QuestionTypeRepository questionTypeRepository;
	
	public List<QuestionType> findAll() {
		return questionTypeRepository.findAll();
	}

	@Transactional
	public void createDefaultTypes() {
		QuestionType single = new QuestionType("Одна відповідь");
		questionTypeRepository.save(single);
		QuestionType multiple = new QuestionType("Кілька відповідей");
		questionTypeRepository.save(multiple);
		QuestionType singleWithOwn = new QuestionType("Одна вдповідь з можливістю обрати свій варіант");
		questionTypeRepository.save(singleWithOwn);
		QuestionType multipleWithOwn = new QuestionType("Кілька відповідей з можливістю обрати свій варіант");
		questionTypeRepository.save(multipleWithOwn);
		QuestionType persentage = new QuestionType("Процентре співвідношення представлених варіантів");
		questionTypeRepository.save(persentage);
		QuestionType persentageWithOwn = new QuestionType("Процентне співвідношення зі своїм варіантом");
		questionTypeRepository.save(persentageWithOwn);
		QuestionType radioTable = new QuestionType("Таблиця з одним варіантом");
		questionTypeRepository.save(radioTable);
		QuestionType multipleTable = new QuestionType("Таблиця з багатьма варіантами");
		questionTypeRepository.save(multipleTable);
		QuestionType persentageTable = new QuestionType("Таблиця процентних співвідношень");
		questionTypeRepository.save(persentageTable);
	}

}
