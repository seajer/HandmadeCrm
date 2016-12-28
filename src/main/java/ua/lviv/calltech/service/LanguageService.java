package ua.lviv.calltech.service;

import java.util.List;

import ua.lviv.calltech.entity.Language;

public interface LanguageService {

	void createLanguage(String name);

	void editLanguage(int id, String name);

	List<Language> findAll();

	void deleteLanguage(int id);

}
