package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.app.models.Vendor;
import com.app.service.IVendorService;

@Controller
public class VendorController {
	@Autowired
	private IVendorService service;

	@RequestMapping("/regVendor")
	public String showRegister() {
		return "VendorRegister";
	}
	@RequestMapping (value="saveVendor",method=RequestMethod.POST)
	public String saveven(@ModelAttribute("Vendor") Vendor ven,ModelMap map)
	{
		int venId=service.saveVendor(ven);
		String info="saved with:"+venId+" successfully";
		map.addAttribute("msg",info);
		return "VendorRegister";
	}
	@RequestMapping("delVendor")
	public String delven(@RequestParam("venId")int venId)
	{
	    service.deleteVendor(venId); 
		return "redirect:getAllVens";
	}
	
    @RequestMapping("editVendor")
    public String editven(@RequestParam("venId")int venId,ModelMap map)
    {
        Vendor ven=service.getVendorById(venId);
        map.addAttribute("ven",ven);
    	return "VendorDataEdit";
    }
    @RequestMapping(value="updateVen",method=RequestMethod.POST)
    public String updateven(@ModelAttribute Vendor ven){
    	service.updateVendor(ven);
    	return "redirect:getAllVens";
    }
	@RequestMapping("getAllVens")
	public String getData(ModelMap map)
	{
	    List<Vendor> vens=service.getAllVendors();
	    map.addAttribute("vens",vens);
		return "VendorData";
	}
}