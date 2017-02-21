package ua.lviv.calltech.service.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ua.lviv.calltech.DTO.SimpleClientObjectDTO;
import ua.lviv.calltech.entity.ClientDataObject;
import ua.lviv.calltech.repository.ClientDataObjectRepository;
import ua.lviv.calltech.service.ClientDataObjectService;

@Service
public class ClientDataObjectServiceImpl implements ClientDataObjectService{

	@Autowired
	private ClientDataObjectRepository clientDataObjectRepositiry;
	
	public ClientDataObject findOneWithResults(int clientId) {
		ClientDataObject client = clientDataObjectRepositiry.findOneWithResults(clientId);
		return client;
	}

	public void setParameter(ClientDataObject client, Cell cell, Map<Integer, String> rules) {
		String value = rules.get(cell.getColumnIndex()+1);
		
		if(value != null){
			switch(value){
			case "Name":
				if(client.getName() == null){
					client.setName(cell.getStringCellValue().trim());
				} else {
					client.setName(client.getName() + ", " + cell.getStringCellValue().trim());
				}
				break;
			case "Surname":
				if(client.getSurname() == null){
					client.setSurname(cell.getStringCellValue().trim());
				} else {
					client.setSurname(client.getSurname() + ", " + cell.getStringCellValue().trim());
				}
				break;
			case "Age":
				if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)client.setAge(String.valueOf(cell.getNumericCellValue()));
				else if(cell.getCellType()==Cell.CELL_TYPE_STRING)client.setAge(cell.getStringCellValue().trim());
				break;
			case "Position":
				if(client.getPosition() == null ){
					client.setPosition(cell.getStringCellValue().trim());
				} else {
					client.setPosition(client.getPosition() + ", " + cell.getStringCellValue().trim());
				}
				break;
			case "Company name":
				if(client.getCompanyName() == null){
					client.setCompanyName(cell.getStringCellValue().trim());
				} else {
					client.setCompanyName(client.getCompanyName() + ", " + cell.getStringCellValue().trim());
				}
				break;
			case "Industry":
				if(client.getIndustry() == null){
					client.setIndustry(cell.getStringCellValue());
				} else {
					client.setIndustry(client.getIndustry() + ", " + cell.getStringCellValue().trim());
				}
				break;
			case "Workers count":
				if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)client.setWorkersCount(String.valueOf(cell.getNumericCellValue()));
				else if(cell.getCellType()==Cell.CELL_TYPE_STRING)client.setWorkersCount(cell.getStringCellValue().trim());
				break;
			case "Years earning":
				if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)client.setYearEarning(String.valueOf(cell.getNumericCellValue()));
				else if(cell.getCellType()==Cell.CELL_TYPE_STRING)client.setYearEarning(cell.getStringCellValue().trim());
				break;
			case "Description":
				if(client.getDescription() == null){
					client.setDescription(cell.getStringCellValue().trim());
				} else {
					client.setIndustry(client.getIndustry() + ", " + cell.getStringCellValue().trim());
				}
				break;
			case "Adress":
				if(client.getAdress() == null){
					client.setAdress(cell.getStringCellValue().trim());
				} else {
					client.setAdress(client.getAdress() + ", " + cell.getStringCellValue().trim());
				}
				break;
			case "Country":
				if(client.getCountry() == null){
					client.setCountry(cell.getStringCellValue().trim());
				} else {
					client.setCountry(client.getCountry() + ", " + cell.getStringCellValue().trim());
				}
				break;
			case "Phone":
				if(client.getPhone() == null){
					if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
						String phone = String.valueOf(cell.getNumericCellValue());
						phone.replace(".", "");
						String normPhone = phone.substring(0, phone.indexOf('E'));
						client.setPhone(normPhone);
					}
					else if(cell.getCellType()==Cell.CELL_TYPE_STRING)client.setPhone(cell.getStringCellValue().trim());
				} else {
					if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
						String phone = String.valueOf(cell.getNumericCellValue());
						String normPhone = phone.substring(0, phone.indexOf('E'));
						client.setPhone(normPhone);
						client.setPhone(client.getPhone() + ", " + normPhone);
					} else if(cell.getCellType()==Cell.CELL_TYPE_STRING){
						client.setPhone(client.getPhone() + ", " + cell.getStringCellValue().trim());
					}
				}
				break;
			case "Email":
				if(client.getEmail() == null){
					client.setEmail(cell.getStringCellValue().trim());
				} else {
					client.setEmail(client.getEmail() + ", " + cell.getStringCellValue().trim());
				}
				break;
			case "Site adress":
				if(client.getSite() == null){
					client.setSite(cell.getStringCellValue().trim());
				} else {
					client.setSite(client.getSite() + ", " + cell.getStringCellValue().trim());
				}
				break;
			case "Comments":
				if(client.getComment() == null){
					client.setComment(cell.getStringCellValue().trim());
				} else {
					client.setComment(client.getComment() + ", " + cell.getStringCellValue().trim());
				}
				break;
			default:
				break;
		}
		}
	}

	public List<ClientDataObject> readFromExcel(Map<Integer, String> map, String fileName, String fileType) {
		FileInputStream fileInputStream = null;
		String server = System.getProperty("catalina.home");
		try {
			fileInputStream = new FileInputStream(new File(server + "/" + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<ClientDataObject> clients = new ArrayList<>();
		if(fileType.equals("xlsx")){
			clients = readXlsxFile(fileInputStream, map);
		} else if(fileType.equals("xls")){
			clients = readXlsFile(fileInputStream, map);
		}
		clientDataObjectRepositiry.save(clients);
		return clients;
	}
	
	public File saveFile(MultipartFile clientFile){
		String server = System.getProperty("catalina.home")+"/";
		File file = new File(server, clientFile.getOriginalFilename());
		try {
			clientFile.transferTo(file);
			System.out.println("FILE SAVED");
		} catch (IllegalStateException e) {
			System.out.println("CANT TRANSFER FILE");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("CANT TRANSFER FILE");
			e.printStackTrace();
		}
		return file;
	}
	
	private List<ClientDataObject> readXlsFile(FileInputStream fileInputStream, Map<Integer, String> map) {
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HSSFSheet sheet = workbook.getSheetAt(0);
		List<ClientDataObject> clientData = new ArrayList<ClientDataObject>();
		for (Row row : sheet) {
			ClientDataObject cdo = new ClientDataObject();
			for (Cell cell : row) {
				setParameter(cdo, cell, map);
			}
			clientData.add(cdo);
		}
		System.out.println("XLS____list size = " + clientData.size());
		return clientData;
	}

	private List<ClientDataObject> readXlsxFile(FileInputStream fileInputStream, Map<Integer, String> map){
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheetAt(0);
		List<ClientDataObject> clientData = new ArrayList<ClientDataObject>();
		for (Row row : sheet) {
			ClientDataObject cdo = new ClientDataObject();
			for (Cell cell : row) {
				setParameter(cdo, cell, map);
			}
			clientData.add(cdo);
			System.out.println(cdo);
		}
		System.out.println("XLSX____list size = " + clientData.size());
		return clientData;
	}

	@Transactional
	public void createOne(String phone, String company) {
		ClientDataObject cdo = new ClientDataObject();
		cdo.setPhone(phone);
		cdo.setCompanyName(company);
		clientDataObjectRepositiry.save(cdo);
	}

	public ClientDataObject findOneByPhoneAndCompany(String phone, String company) {
		ClientDataObject cdo = clientDataObjectRepositiry.findByPhoneAndCompany(phone, company);
		return cdo;
	}

	public List<SimpleClientObjectDTO> findAllByProjectId(int projectId) {
		return clientDataObjectRepositiry.findAllDTOByProjectId(projectId);
	}

	public int findIdByResultId(int resultId) {
		return clientDataObjectRepositiry.findIdByResultId(resultId);
	}

	@Transactional
	public void save(ClientDataObject object) {
		ClientDataObject cdo = clientDataObjectRepositiry.findOne(object.getId());
		if(cdo != null){
			cdo.setAdress(object.getAdress());
			cdo.setAge(object.getAge());
			cdo.setComment(object.getComment());
			cdo.setCompanyName(object.getCompanyName());
			cdo.setCountry(object.getCountry());
			cdo.setDescription(object.getDescription());
			cdo.setEmail(object.getEmail());
			cdo.setIndustry(object.getIndustry());
			cdo.setName(object.getName());
			cdo.setPhone(object.getPhone());
			cdo.setPosition(object.getPosition());
			cdo.setSite(object.getSite());
			cdo.setSurname(object.getSurname());
			cdo.setWorkersCount(object.getWorkersCount());
			cdo.setYearEarning(object.getYearEarning());
			clientDataObjectRepositiry.save(cdo);
		}
	}

}
