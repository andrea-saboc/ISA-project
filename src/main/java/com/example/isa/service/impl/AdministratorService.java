package com.example.isa.service.impl;


import com.example.isa.dto.AccountDeletionDTO;
import com.example.isa.dto.AdministratorRegistrationDto;
import com.example.isa.dto.ReportAcceptDeletedAccountDTO;
import com.example.isa.dto.ReportAdminDto;
import com.example.isa.exception.BadUserTypeException;
import com.example.isa.mail.MailService;
import com.example.isa.model.*;
import com.example.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoatOwnerRepository boatOwnerRepository;
    @Autowired
    private FishingInstructorRepository fishingInstructorRepository;
    @Autowired
    private MansionOwnerRepository mansionOwner;
    @Autowired
    private MailService mailService;
    @Autowired
    private DeletionRequestRepository deletionRequestRepository;
    @Autowired
    private MansionRepository mansionRepository;
    @Autowired
    private BoatRepository boatRepository;
    @Autowired
    private AdventureRepository adventureRepository;


    public User saveNewAdministrator(AdministratorRegistrationDto dat)throws BadUserTypeException
    {
        dat.setPassword(passwordEncoder.encode(dat.getPassword()));
        Object user = null;
        List<Authority> authorities = new ArrayList<>();
        Authority auth = null;
        user=dat.createAdministrator();
        auth = roleRepository.findByName("ROLE_ADMIN");
        authorities.add(auth);
        Administrator administrator = (Administrator) user;
        administrator.setAuthorities(authorities);
        administratorRepository.save(administrator);
        return (User) administrator;
    }

    public List<User> getAllUsersWithoutAdmin()
    {
        List<User> users= new ArrayList<>();
        List <User> allUsers= (List<User>) userRepository.findAll();
        for(User u: allUsers)
        {
            if(!(u.getClass().getSimpleName().equals("Administrator") || u.getClass().getSimpleName().equals("Client")))
                users.add(u);
        }
        return users;
    }
    public void unapproveRegistration(ReportAdminDto dta)
    {
        User u=userRepository.findByEmail(dta.getEmail());
        if(u.getClass().getSimpleName().equals("MansionOwner"))
        {
            MansionOwner m=(MansionOwner) u;
            m.setApproved(dta.isApproved());
            m.setNotapproved(true);
            mansionOwner.save(m);
          //  mailService.sendNotificationAbaoutUnapprovedRegistration(u, dta.getReportText());
        }
        if(u.getClass().getSimpleName().equals("FishingInstructor"))
        {
            FishingInstructor m=(FishingInstructor) u;
            m.setApproved(dta.isApproved());
            m.setNotapproved(true);
            fishingInstructorRepository.save(m);
          //  mailService.sendNotificationAbaoutUnapprovedRegistration(u, dta.getReportText());
        }
        if(u.getClass().getSimpleName().equals("BoatOwner"))
        {
            BoatOwner m=(BoatOwner) u;
            m.setApproved(dta.isApproved());
            m.setNotapproved(true);
            boatOwnerRepository.save(m);
          //  mailService.sendNotificationAbaoutUnapprovedRegistration(u, dta.getReportText());
        }
    }
    public void approveRegistration(ReportAdminDto dta)
    {
        User u=userRepository.findByEmail(dta.getEmail());
        if(u.getClass().getSimpleName().equals("MansionOwner"))
        {
            MansionOwner m=(MansionOwner) u;
            m.setApproved(dta.isApproved());
            m.setNotapproved(false);
            mansionOwner.save(m);
            //  mailService.sendNotificationAbaoutUnapprovedRegistration(u, dta.getReportText());
        }
        if(u.getClass().getSimpleName().equals("FishingInstructor"))
        {
            FishingInstructor m=(FishingInstructor) u;
            m.setApproved(dta.isApproved());
            m.setNotapproved(false);
            fishingInstructorRepository.save(m);
            //  mailService.sendNotificationAbaoutUnapprovedRegistration(u, dta.getReportText());
        }
        if(u.getClass().getSimpleName().equals("BoatOwner"))
        {
            BoatOwner m=(BoatOwner) u;
            m.setApproved(dta.isApproved());
            m.setNotapproved(false);
            boatOwnerRepository.save(m);
            //  mailService.sendNotificationAbaoutUnapprovedRegistration(u, dta.getReportText());
        }
    }

    public List<AccountDeletionDTO> getAll()
    {
        List<AccountDeletionRequest> accountDeletionRequests=deletionRequestRepository.findAll();
        List<AccountDeletionDTO> list=new ArrayList<>();
       for(AccountDeletionRequest a: accountDeletionRequests)
       {
           User u=userRepository.findById(a.getClientId()).get();
           AccountDeletionDTO dto=new AccountDeletionDTO(u.getId(),u.getName(),u.getSurname(),a.getReason(),u.getClass().getSimpleName(),u.isDeleted(),u.isNotdeleted());
           list.add(dto);
       }
       return list;
    }
    public void acceptAccountDeleted(Long id)
    {
        User u=userRepository.findById(id).get();
        u.setDeleted(true);
        u.setNotdeleted(false);
        userRepository.save(u);
    //   mailService.sendNotificationAbaoutDeletedAccount(u);
    }
    public void unacceptAccountDeleted(ReportAcceptDeletedAccountDTO dto)
    {
        User u=userRepository.findById(dto.getId()).get();
        u.setDeleted(false);
        u.setNotdeleted(true);
        userRepository.save(u);
        //mailService.sendNotificationAbaoutUndeletedAccount(u, dto.getReportText());

    }

    public void deletedUser(Long id)
    {
        User user=userRepository.findById(id).get();

        if(user.getClass().getSimpleName().equals("MansionOwner"))
        {
            List<Mansion> allMansion=mansionRepository.findAllByMansionOwner((MansionOwner) user);
            for (Mansion m : allMansion)
            {
                m.setDeleted(true);
                mansionRepository.save(m);
            }
        }
        if(user.getClass().getSimpleName().equals("FishingInstructor"))
        {
            List<Adventure>adventures=adventureRepository.findAdventureByFishingInstructor((FishingInstructor) user);
            for(Adventure a : adventures)
            {
                a.setDeleted(true);
                adventureRepository.save(a);
            }
        }
        if(user.getClass().getSimpleName().equals("BoatOwner"))
        {
            List<Boat>boats=boatRepository.findBoatByBoatOwner((BoatOwner) user);
            for(Boat a : boats)
            {
                a.setDeleted(true);
                boatRepository.save(a);
            }
        }
        user.setDeleted(true);
        userRepository.save(user);
    }

}
