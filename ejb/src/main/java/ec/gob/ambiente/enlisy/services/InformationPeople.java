package ec.gob.ambiente.enlisy.services;

import java.util.List;

import javax.ejb.EJB;

import lombok.Getter;
import lombok.Setter;
import ec.gob.ambiente.enlisy.model.Contact;
import ec.gob.ambiente.enlisy.model.ContactsForm;
import ec.gob.ambiente.enlisy.model.People;
import ec.gob.ambiente.exceptions.ServiceException;



public class InformationPeople {
	@EJB
	private ContactFacade contactFacade;
	
	List<Contact> contactosList;
	List<Contact> contactosOrgList;
	List<Contact> contactosRepresentanteList;
	
	@Getter
	@Setter
	private Contact phone, cellular, address, email, email2;
	
	@SuppressWarnings("unused")
	private void contactosListener(People people ) {
		// List<Contact> contactosList;
		try {
			contactosList = contactFacade.findByPeople(people);
			boolean isEmail1 = false, isEmail2 = false;
			for (Contact contact : contactosList) {
				int tipoContacto = Integer.valueOf(contact.getContactsForm()
						.getCofoId().intValue());

				switch (tipoContacto) {
				case ContactsForm.CELULAR:
					this.setCellular(contact);
					break;
				case ContactsForm.TELEFONO:
					setPhone(contact);
					break;
				case ContactsForm.DIRECCION:
					setAddress(contact);
					break;
				case ContactsForm.EMAIL:
					if (isEmail1 == false && isEmail2 == false) {
						setEmail(contact);
						isEmail1 = true;
					} else if (isEmail1 == true && isEmail2 == false) {
						setEmail2(contact);
						isEmail2 = true;
					}
					break;
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
