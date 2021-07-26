package cl.christian.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.christian.demo.interfaceService.IRedSocialService;
import cl.christian.demo.interfaces.IRedSocial;
import cl.christian.demo.modelo.RedSocial;
@Service
public class RedesSocialesService implements IRedSocialService {

	@Autowired
	private IRedSocial redsocialRepo;

	@Override
	public void guardar(RedSocial redSocial) {
		
		redsocialRepo.save(redSocial);
	}

	@Override
	public RedSocial listaPorRedSocial(String username) {
		
		return redsocialRepo.findByUsername(username);
	}

}
