package cl.christian.demo.interfaceService;

import cl.christian.demo.modelo.RedSocial;

public interface IRedSocialService {
void guardar(RedSocial redSocial);
public RedSocial listaPorRedSocial(String username);
}
