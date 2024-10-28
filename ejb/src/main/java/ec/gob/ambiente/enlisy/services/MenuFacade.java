package ec.gob.ambiente.enlisy.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ec.gob.ambiente.enlisy.dao.AbstractFacade;
import ec.gob.ambiente.enlisy.model.Menu;
import ec.gob.ambiente.enlisy.model.RolesUser;
import ec.gob.ambiente.vo.MenuVO;

@Stateless
public class MenuFacade extends AbstractFacade<Menu, Integer> implements Serializable{

    private static final long serialVersionUID = 1551132779840660637L;

    public MenuFacade() {
        super(Menu.class, Integer.class);        
    }
    
    /**
     * Buscar por MNEMONIC
     * @param mnemonic
     * @return
     */
    public Menu findByMnemonic(String mnemonic)
    {
        TypedQuery<Menu> query = super.getEntityManager().createQuery("select o from Menu o where o.menuStatus = true and o.menuMnemonic = :mnemonic", Menu.class);        
        query.setParameter("mnemonic", mnemonic);
        return (Menu)query.getSingleResult();
        
    }
    
    /**
     * Buscar por roles
     * @param roles
     * @param menuMnemonic
     * @return
     */
    @SuppressWarnings("unchecked")
	private List<Menu> findByRoles(List<Integer> roles, String menuMnemonic)
    {
        List<Menu> menus = new ArrayList<Menu>();
        try{
            if(roles.size() > 0)
            {
                Query queryIds = super.getEntityManager().createQuery("select distinct mr.menu.menuId from MenuRole mr where mr.meroStatus=true and mr.role.roleId in :roles and (mr.menu.menuMnemonic != :menuMnemonic or mr.menu.menuMnemonic is null) and mr.menu.menuSampleMenu = true");
                queryIds.setParameter("roles", roles);
                queryIds.setParameter("menuMnemonic", menuMnemonic);
                List<Integer> menuIds = (List<Integer>) queryIds.getResultList();
                
                Query query =super.getEntityManager().createQuery("select m from Menu m where m.menuId in (:menuIds) order by m.menuOrder");
                query.setParameter("menuIds", menuIds);
                menus= (List<Menu>) query.getResultList();
                
                return menus;
            }
        }catch(Exception e){
            System.out.println("Error en 'private List<Menu> findByRoles(List<Integer> roles, String menuMnemonic)':::: "+e.getMessage());
            e.printStackTrace();
        }
        

        return menus;
        
    }
        
    /**
     * Buscar por roles
     * @param rolesUsers
     * @param menuMnemonic
     * @return MenuVO
     */
    public List<MenuVO> getMenusByRoles(List<RolesUser> rolesUsers, String menuMnemonic)
    {
        try {
            
        
            List<MenuVO> menus = new ArrayList<MenuVO>();
            Integer userId = 0;
            Integer parentMenu = 0;
            List<Integer> listaRoles = new ArrayList<Integer>();
            for (RolesUser rolUser : rolesUsers) {
                userId = rolUser.getUser().getUserId();
                listaRoles.add(rolUser.getRole().getRoleId());
            }
        
            List<Menu> menusList =findByRoles(listaRoles, menuMnemonic);
            for (Menu menu : menusList) {
            	if(menu.getMenu() != null){
                    parentMenu = (menu.getMenu().getMenuId() != null || menu.getMenu().getMenuId() != 0) ? menu.getMenu().getMenuId() : 0;
                    menus.add(new MenuVO(menu.getMenuId(), menu.getMenuName(), menu.getMenuAction(), menu.getMenuUrl(), parentMenu, userId, menu.getMenuEndNode(), menu.getMenuOrder(), menu.getMenuIcon()));
            	}
            }
        
            return menus;
        
        } catch (Exception e) {
            return null;
        }
    }
    

}