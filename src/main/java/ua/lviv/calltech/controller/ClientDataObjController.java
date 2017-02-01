package ua.lviv.calltech.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ua.lviv.calltech.entity.ClientDataObject;
import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.entity.Status;
import ua.lviv.calltech.service.ClientDataObjectService;
import ua.lviv.calltech.service.ProjectService;
import ua.lviv.calltech.service.StatusService;

@Controller
public class ClientDataObjController {

	@Autowired
	private StatusService statusService;

	@Autowired
	private ClientDataObjectService clientDataObectService;

	@Autowired
	private ProjectService projectService;

	@RequestMapping(value="/examined_p={projectId}r={resultId}", method=RequestMethod.GET)
	public String fillExaminedValues(@PathVariable("projectId")int projectId, @PathVariable("resultId")int resultId, Model model){
		List<Status> statuses = statusService.findAll();
		if(statuses.size()<1){
			statusService.setDefaultStatuses();
			statuses = statusService.findAll();
		}
		model.addAttribute("projectId", projectId).addAttribute("resultId", resultId);
		model.addAttribute("status", statuses).addAttribute("clientDataObject", new ClientDataObject());
		return "client-new";
	}
	
	@RequestMapping(value="/saveExamined", method = RequestMethod.POST)
	public String saveExamined(@RequestParam("projectId")int projectId, @RequestParam("resultId")int resultId,
			@ModelAttribute("clientDataObject")ClientDataObject object){
		clientDataObectService.save(object, projectId, resultId);
		return "redirect:/all_projects";
	}
	
	@RequestMapping(value="/edit_client_{clientId}", method = RequestMethod.GET)
	public String editClient(@PathVariable("clientId")int clientId, Model model){
		ClientDataObject client = clientDataObectService.findOneWithResults(clientId);
		if(client != null){
			model.addAttribute("clientDataObject", client);
			return "client-edit";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/editExamined", method = RequestMethod.POST)
	public String editExamined(@ModelAttribute("clientDataObject")ClientDataObject object, BindingResult br){
		clientDataObectService.save(object);
		return "redirect:/all_projects";
	}
	
	@RequestMapping(value="/project_clients_{projectId}", method = RequestMethod.GET)
	public String allProjectClients(@PathVariable("projectId")int projectId, Model model){
		Project project = projectService.findOneWithType(projectId);
		if(project != null){
			model.addAttribute("project", project);
			//List<SimpleClientObjectDTO> clients = clientDataObectService.findAllByProjectIdWithResults(projectId);
			//model.addAttribute("clients", clients);
			return "client-all";
		}
		return "404";
	}
	
	@RequestMapping(value="/uploadCustomerDB", method = RequestMethod.GET)
	public String uploadCustomersDB( Model model){
		model.addAttribute("CDOFields", ClientDataObjectService.clientDataObjectParams);
		String[] types = {"xlsx", "xls"};
		model.addAttribute("types", types);
		return "client-db";
	}
	
	@RequestMapping(value="/save_customer_DB", method = RequestMethod.POST)
	public String saveCustomersDB(@RequestParam("paramName")String[] paramNames, @RequestParam("paramNumber")int[] paramNumbers,
			@RequestParam("type")String type, @RequestParam("file") MultipartFile file){
		Map<Integer, String> customerDBChain = new HashMap<>();
		for(int i = 0; i < paramNames.length; i++){
			customerDBChain.put(paramNumbers[i], paramNames[i]);
		}
		File savedFile = clientDataObectService.saveFile(file);
		clientDataObectService.readFromExcel(customerDBChain, file.getOriginalFilename(), type);
		if(savedFile.delete()){
			System.out.println(savedFile.getName() + " is deleted!");
		}else{
			System.out.println("Delete operation is failed.");
		}
		return "redirect:/";
	}
}
