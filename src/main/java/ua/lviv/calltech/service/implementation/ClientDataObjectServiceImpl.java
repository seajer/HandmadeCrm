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

import ua.lviv.calltech.entity.ClientDataObject;
import ua.lviv.calltech.repository.ClientDataObjectRepository;
import ua.lviv.calltech.service.ClientDataObjectService;

@Service
public class ClientDataObjectServiceImpl implements ClientDataObjectService{

	@Autowired
	private ClientDataObjectRepository clientDataObjectRepositiry;
	
//	@Autowired
//	private ProjectService projectService;
//	
//	@Autowired
//	private ResultService resultService;
//	
//	@Autowired
//	private StatusService statusService;

	@Transactional
	public void save(ClientDataObject object, int projectId, int resultId) {
		// TODO: rewrite this method
	}

	@Transactional
	public void save(ClientDataObject object) {
		// TODO: rewrite this method
	}

//	public List<SimpleClientObjectDTO> findAllByProjectIdWithResults(int projectId) {
//		List<SimpleClientObjectDTO> object = clientDataObjectRepositiry.findSimpleClientsByProjectId(projectId);
//		return object;
//	}
	
	@Transactional
	public ClientDataObject findOneWithStatusAndProject(int clientId) {
//		ClientDataObject client = clientDataObjectRepositiry.findOneWithStatusAndProject(clientId);
		return null;
	}

	public void setParameter(ClientDataObject client, Cell cell, Map<Integer, String> rules) {
		String value = rules.get(cell.getColumnIndex()+1);
		
		if(value != null){
			switch(value){
			case "Name":
				client.setName(cell.getStringCellValue().trim());
				break;
			case "Surname":
				client.setSurname(cell.getStringCellValue().trim());
				break;
			case "Age":
				if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)client.setAge(String.valueOf(cell.getNumericCellValue()));
				else if(cell.getCellType()==Cell.CELL_TYPE_STRING)client.setAge(cell.getStringCellValue().trim());
				break;
			case "Position":
				client.setAdress(cell.getStringCellValue().trim());
				break;
			case "Company name":
				client.setCompanyName(cell.getStringCellValue().trim());
				break;
			case "Industry":
				client.setIndustry(cell.getStringCellValue());
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
				client.setDescription(cell.getStringCellValue().trim());
				break;
			case "Adress":
				client.setAdress(cell.getStringCellValue().trim());
				break;
			case "Country":
				client.setCountry(cell.getStringCellValue().trim());
				break;
			case "Phone":
				if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)client.setPhone(String.valueOf(cell.getNumericCellValue()));
				else if(cell.getCellType()==Cell.CELL_TYPE_STRING)client.setPhone(cell.getStringCellValue().trim());
				break;
			case "Email":
				client.setEmail(cell.getStringCellValue().trim());
				break;
			case "Site adress":
				client.setSite(cell.getStringCellValue().trim());
				break;
			case "Comments":
				client.setComment(cell.getStringCellValue().trim());
				break;
			default:
				break;
		}
		}
	}

	public void readFromExcel(Map<Integer, String> map, String fileName, String fileType) {
		FileInputStream fileInputStream = null;
		String server = System.getProperty("catalina.home");
		try {
			fileInputStream = new FileInputStream(new File(server + "/" + fileName + "." + fileType));
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
		}
		System.out.println("XLSX____list size = " + clientData.size());
		return clientData;
	}

}
