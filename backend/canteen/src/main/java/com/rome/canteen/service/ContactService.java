package com.rome.canteen.service;

import com.rome.canteen.model.Contact;
import com.rome.canteen.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // Save contact form data
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
}
