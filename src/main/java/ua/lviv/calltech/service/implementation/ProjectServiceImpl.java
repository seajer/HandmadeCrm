package ua.lviv.calltech.service.implementation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.DTO.ProjectDTO;
import ua.lviv.calltech.DTO.QuestionnaireDTO;
import ua.lviv.calltech.DTO.UserDTO;
import ua.lviv.calltech.entity.Answer;
import ua.lviv.calltech.entity.ClientDataObject;
import ua.lviv.calltech.entity.Language;
import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.ProjectType;
import ua.lviv.calltech.entity.Question;
import ua.lviv.calltech.entity.Result;
import ua.lviv.calltech.entity.SingleResult;
import ua.lviv.calltech.entity.User;
import ua.lviv.calltech.repository.LanguageRepository;
import ua.lviv.calltech.repository.ProjectRepository;
import ua.lviv.calltech.repository.ProjectTypeRepository;
import ua.lviv.calltech.repository.QuestionnaireRepository;
import ua.lviv.calltech.repository.UserRepository;
import ua.lviv.calltech.service.ProjectService;
import ua.lviv.calltech.service.QuestionService;
import ua.lviv.calltech.service.ResultService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private LanguageRepository langRepository;

	@Autowired
	private ProjectTypeRepository pTypeRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private ResultService resultService;

	@Transactional
	public void addProject(String title, String company, int langId, int typeId) {
		Project project = new Project(company, title);
		Language lang = langRepository.findOne(langId);
		ProjectType pt = pTypeRepository.findOne(typeId);
		project.setLanguage(lang);
		project.setType(pt);
		projectRepository.save(project);
	}

	@Transactional
	public List<Project> findAllWithLanguageAndType() {
		return projectRepository.findAllWithLanguageAndType();
	}

	@Transactional
	public void deleteProject(int projectId) {
		if (projectRepository.findOne(projectId) != null) {
			projectRepository.delete(projectId);
		}
	}

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Transactional
	public ProjectDTO findDtoById(int projectId) {
		ProjectDTO project = projectRepository.findDtoById(projectId);
		if (project != null) {
			List<UserDTO> projectUsers = userRepository.findAllDtoFromProject(projectId);
			QuestionnaireDTO questionnaire = questionnaireRepository.findDtoByProjectId(projectId);
			project.setUsers(projectUsers);
			project.setQuestionnaire(questionnaire);
		}
		return project;
	}

	@Transactional
	public void editProject(int projectId, String title, String company, int langId, int typeId, int[] usersId) {
		Project project = projectRepository.findOne(projectId);
		if (project != null) {
			Language lang = langRepository.findOne(langId);
			ProjectType type = pTypeRepository.findOne(typeId);
			project.setTitle(title);
			project.setCompanyName(company);
			List<User> users = new ArrayList<User>();
			for (int i : usersId) {
				User u = userRepository.findOne(i);
				users.add(u);
			}
			project.setUsers(users);
			if (lang != null) {
				project.setLanguage(lang);
			}
			if (type != null) {
				project.setType(type);
			}
			projectRepository.save(project);
		}
	}

	public Project findOne(int projectId) {
		return projectRepository.findOne(projectId);
	}

	@Transactional
	public Project findOneWithType(int projectId) {
		Project project = projectRepository.findOneWithType(projectId);
		return project;
	}

	@Transactional
	public Integer findIdByResultId(int resultId) {
		Integer projectId = projectRepository.findIdByResultId(resultId);
		return projectId;
	}

	public Project findByNaneAndCompany(String name, String company) {
		Project project = projectRepository.findByNameAndCompany(name, company);
		return project;
	}

	public void generateOutput(Map<Integer, String> map, int projectId) {
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("sheet 1");

		Project project = projectRepository.findOne(projectId);
		List<Question> questions = questionService.findQuestionsByProjectId(projectId);
		List<Result> results = resultService.findAllFinishedByProject(projectId);

		generateHeader(map.keySet(), sheet, questions);
		generateColumNames(map, sheet, questions);

		int rowNumber = 2;

		for (Result result : results) {
			Row row = sheet.createRow(rowNumber++);

			createUserDataColumns(row, result.getClient(), map);
			createSingleResultsColumns(row, result.getResults(), questions);
		}

		try (FileOutputStream outputStream = new FileOutputStream(project.getTitle() + ".xlsx")) {
			workbook.write(outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createSingleResultsColumns(Row row, List<SingleResult> results, List<Question> questions) {
		int cellNum = row.getLastCellNum();
		for (SingleResult singleResult : results) {
			int qType = singleResult.getQuestion().getType().getId();
			int qId = singleResult.getQuestion().getId();
			if(Arrays.asList(1,2).contains(qType)){
				for (Question question : questions) {
					if(question.getId()==qId){
						for(int i = 0; i < question.getAnswers().size(); i++){
							Cell c = row.createCell(++cellNum);
							if(singleResult.getAnswers().contains(question.getAnswers().get(i)))c.setCellValue("x");
						}
						break;
					}
				}
			}else if(Arrays.asList(3, 4).contains(qType)){
				for (Question question : questions) {
					if(question.getId()==qId){
						for(int i = 0; i < question.getAnswers().size(); i++){
							Cell c = row.createCell(++cellNum);
							if(singleResult.getAnswers().contains(question.getAnswers().get(i)))c.setCellValue("x");
						}
						Cell c = row.createCell(++cellNum);
						if(singleResult.getCustomAnswer().length() > 1)c.setCellValue(singleResult.getCustomAnswer());
						break;
					}
				}
			}else if(qType == 5){
				for (Question question : questions) {
					if(question.getId()==qId){
						String[] answers = singleResult.getCustomAnswer().split(",");
						for(int i = 0; i < question.getAnswers().size(); i++){
							Cell c = row.createCell(++cellNum);
							c.setCellValue(answers[i]);
						}
						break;
					}
				}
			}else if(qType == 6){
				for (Question question : questions) {
					if(question.getId()==qId){
						String[] answers = singleResult.getCustomAnswer().split(",");
						for(int i = 0; i < question.getAnswers().size(); i++){
							Cell c = row.createCell(++cellNum);
							c.setCellValue(answers[i]);
						}
						if(answers.length > question.getAnswers().size()){
							Cell c = row.createCell(++cellNum);
							c.setCellValue(answers[answers.length-1]);
						}
						break;
					}
				}
			}else if(qType == 7){
				Cell c = row.createCell(++cellNum);
				if(singleResult.getCustomAnswer().length()>1)c.setCellValue(singleResult.getCustomAnswer());
			}
		}
	}

	private void generateHeader(Set<Integer> keys, XSSFSheet sheet, List<Question> questions) {
		Row row1 = sheet.createRow(0);
		Cell c = row1.createCell(0);
		c.setCellValue("Users data");
		for (int i = 1; i < keys.size(); i++) {
			row1.createCell(i);
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, keys.size() - 1));
		for (Question question : questions) {
			int typeId = question.getType().getId();
			if (Arrays.asList(1, 2, 5).contains(typeId)) {
				int first = row1.getFirstCellNum();
				Cell c1 = row1.createCell(++first);
				c1.setCellValue(question.getText());
				for (int i = 1; i < question.getAnswers().size(); i++) {
					row1.createCell(first + i);
				}
				sheet.addMergedRegion(new CellRangeAddress(0, 0, first, (first + question.getAnswers().size() - 1)));
			} else if (Arrays.asList(3, 4, 6).contains(typeId)) {
				int first = row1.getFirstCellNum();
				Cell c1 = row1.createCell(++first);
				c1.setCellValue(question.getText());
				for (int i = 1; i <= question.getAnswers().size(); i++) {
					row1.createCell(first + i);
				}
				sheet.addMergedRegion(new CellRangeAddress(0, 0, first, (first + question.getAnswers().size())));
			} else if (typeId == 7) {
				Cell c1 = row1.createCell(row1.getFirstCellNum() + 1);
				c1.setCellValue(question.getText());
			} else if (Arrays.asList(8, 9, 10).contains(typeId)) {
				for (Question q : question.getTableQuestions()) {
					int first = row1.getFirstCellNum();
					Cell c1 = row1.createCell(++first);
					c1.setCellValue(question.getText());
					for (int i = 1; i < q.getAnswers().size(); i++) {
						row1.createCell(first + i);
					}
					sheet.addMergedRegion(
							new CellRangeAddress(0, 0, first, (first + question.getAnswers().size() - 1)));
				}
			}
		}
	}

	private void generateColumNames(Map<Integer, String> map, XSSFSheet sheet, List<Question> questions) {
		Row row2 = sheet.createRow(1);
		int cellNumb = 0;
		for (Integer i : map.keySet()) {
			Cell c = row2.createCell(cellNumb++);
			c.setCellValue(map.get(i));
		}

		for (Question question : questions) {
			int typeId = question.getType().getId();
			if (Arrays.asList(1, 2, 5).contains(typeId)) {
				for (Answer answer : question.getAnswers()) {
					Cell cell = row2.createCell(cellNumb++);
					cell.setCellValue(answer.getText());
				}
			} else if (Arrays.asList(3, 4, 6).contains(typeId)) {
				for (Answer answer : question.getAnswers()) {
					Cell cell = row2.createCell(cellNumb++);
					cell.setCellValue(answer.getText());
				}
				Cell cell = row2.createCell(cellNumb++);
				cell.setCellValue("Other");
			} else if (typeId == 7) {
				Cell cell = row2.createCell(cellNumb++);
				cell.setCellValue(question.getText());
			} else if (Arrays.asList(8, 9, 10).contains(typeId)) {
				for (Question q : question.getTableQuestions()) {
					Cell cell = row2.createCell(cellNumb++);
					cell.setCellValue(q.getText());
				}
			}
		}
	}

	private void createUserDataColumns(Row row, ClientDataObject cdo, Map<Integer, String> map) {
		int lstNumb = row.getLastCellNum();
		for (Integer cell : map.keySet()) {
			Cell c = row.createCell(++lstNumb);
			setCDOValueIntoCell(c, map.get(cell), cdo);
		}
	}

	private void setCDOValueIntoCell(Cell c, String string, ClientDataObject cdo) {
		switch (string) {
		case "Name":
			c.setCellValue(cdo.getName());
			break;
		case "Surname":
			c.setCellValue(cdo.getSurname());
			break;
		case "Age":
			c.setCellValue(cdo.getAge());
			break;
		case "Position":
			c.setCellValue(cdo.getPosition());
			break;
		case "Company name":
			c.setCellValue(cdo.getCompanyName());
			break;
		case "Industry":
			c.setCellValue(cdo.getIndustry());
			break;
		case "Workers count":
			c.setCellValue(cdo.getWorkersCount());
			break;
		case "Years earning":
			c.setCellValue(cdo.getYearEarning());
			break;
		case "Description":
			c.setCellValue(cdo.getDescription());
			break;
		case "Adress":
			c.setCellValue(cdo.getAdress());
			break;
		case "Country":
			c.setCellValue(cdo.getCountry());
			break;
		case "Phone":
			c.setCellValue(cdo.getPhone());
			break;
		case "Email":
			c.setCellValue(cdo.getEmail());
			break;
		case "Site adress":
			c.setCellValue(cdo.getSite());
			break;
		case "Comments":
			c.setCellValue(cdo.getComment());
			break;
		}
	}
}
