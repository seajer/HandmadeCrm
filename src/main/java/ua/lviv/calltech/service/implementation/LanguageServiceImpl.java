package ua.lviv.calltech.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.calltech.DTO.ProjectDTO;
import ua.lviv.calltech.entity.Language;
import ua.lviv.calltech.repository.LanguageRepository;
import ua.lviv.calltech.service.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository langRepository;
	
	@Transactional
	public void createLanguage(String name) {
		Language lang = new Language();
		lang.setName(name);
		langRepository.save(lang);
	}

	@Transactional
	public void editLanguage(int id, String name) {
		Language lang = langRepository.findOne(id);
		if(lang != null ){
			lang.setName(name);
			langRepository.save(lang);
		}
	}

	public List<Language> findAll() {
		return langRepository.findAll();
	}

	@Transactional
	public void deleteLanguage(int id) {
		Language lang = langRepository.findOne(id);
		if(lang != null){
			langRepository.delete(id);
		}
	}

	@Transactional
	public List<Language> languagesExceptProjects(ProjectDTO project) {
		List<Language> all = findAll();
		Language projectsLang = langRepository.projectsLanguage(project.getId());
		project.setLanguage(projectsLang);
		all.remove(projectsLang);
		return all;
	}

	public List<Language> findAllByUserId(int userId) {
		List<Language> langs = langRepository.findAllByUserId(userId);
		return langs;
	}

	public List<Language> findAllExcept(List<Language> langsIn) {
		List<Language> all = findAll();
		all.removeAll(langsIn);
		return all;
	}

}
