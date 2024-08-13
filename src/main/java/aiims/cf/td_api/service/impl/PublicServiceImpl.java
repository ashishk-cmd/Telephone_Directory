package aiims.cf.td_api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiims.cf.td_api.config.AppConstants;
import aiims.cf.td_api.modal.Helpdesk;
import aiims.cf.td_api.repository.HelpdeskRepository;
import aiims.cf.td_api.service.PublicService;

@Service
public class PublicServiceImpl implements PublicService {

	@Autowired
	private HelpdeskRepository helpdeskRepository;
	
	@Override
	public List<Helpdesk> getAllHelpdeskNumbers() {
		List<Helpdesk> helpdesk = this.helpdeskRepository.findAllByStatus(AppConstants.ACTIVE);
		return helpdesk;
	}

}
