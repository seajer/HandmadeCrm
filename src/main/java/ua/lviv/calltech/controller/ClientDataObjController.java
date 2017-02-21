package ua.lviv.calltech.controller;

import java.io.File;
import java.security.Principal;
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

import ua.lviv.calltech.DTO.SimpleClientObjectDTO;
import ua.lviv.calltech.entity.ClientDataObject;
import ua.lviv.calltech.entity.Project;
import ua.lviv.calltech.service.ClientDataObjectService;
import ua.lviv.calltech.service.ProjectService;
import ua.lviv.calltech.service.ResultService;

@Controller
public class ClientDataObjController {

	@Autowired
	private ClientDataObjectService clientDataObectService;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ResultService resultService;
	
	@RequestMapping(value="/edit_client_{clientId}", method = RequestMethod.GET)
	public String editClient(@PathVariable("clientId")int clientId, Model model, Principal principal){
		if(principal == null) return "redirect:/loginpage";
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
	public String allProjectClients(@PathVariable("projectId")int projectId, Model model, Principal principal){
		if(principal == null) return "redirect:/loginpage";
		Project project = projectService.findOneWithType(projectId);
		if(project != null){
			model.addAttribute("project", project);
			List<SimpleClientObjectDTO> clients = clientDataObectService.findAllByProjectId(projectId);
			model.addAttribute("clients", clients);
			return "client-all";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/uploadCustomerDB_{projectId}", method = RequestMethod.GET)
	public String uploadCustomersDB(@PathVariable("projectId")int projectId, Model model, Principal principal){
		if(principal == null) return "redirect:/loginpage";
		model.addAttribute("CDOFields", ClientDataObjectService.clientDataObjectParams);
		String[] types = {"xlsx", "xls"};
		model.addAttribute("types", types).addAttribute("projectId", projectId);
		return "project-db";
	}
	
	@RequestMapping(value="/save_customer_DB", method = RequestMethod.POST)
	public String saveCustomersDB(@RequestParam("paramName")String[] paramNames, @RequestParam("paramNumber")int[] paramNumbers,
			@RequestParam("type")String type, @RequestParam("projectId")int projectId, @RequestParam("file") MultipartFile file){
		Map<Integer, String> customerDBChain = new HashMap<>();
		for(int i = 0; i < paramNames.length; i++){
			customerDBChain.put(paramNumbers[i], paramNames[i]);
		}
		File savedFile = clientDataObectService.saveFile(file);
		List<ClientDataObject> clients = clientDataObectService.readFromExcel(customerDBChain, file.getOriginalFilename(), type);
		resultService.createResultsForCDOs(clients, projectId);
		if(savedFile.delete()){
			System.out.println(savedFile.getName() + " is deleted!");
		}else{
			System.out.println("Delete operation is failed.");
		}
		return "redirect:/";
	}
}
