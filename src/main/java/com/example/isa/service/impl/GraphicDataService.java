package com.example.isa.service.impl;

import com.example.isa.dto.*;
import com.example.isa.model.*;
import com.example.isa.model.reservations.*;
import com.example.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class GraphicDataService {
    @Autowired
    BoatOwnerRepository boatOwnerRepository;
    @Autowired
    MansionOwnerRepository mansionOwnerRepository;
    @Autowired
    FishingInstructorRepository fishingInstructorRepository;
    @Autowired
    BoatRepository boatRepository;
    @Autowired
    MansionRepository mansionRepository;
    @Autowired
    AdventureRepository adventureRepository;
    @Autowired
    BoatReservationRepository boatReservationRepository;
    @Autowired
    MansionReservationRepository mansionReservationRepository;
    @Autowired
    AdventureReservationRepository adventureReservationRepository;
    @Autowired
    BoatDiscountReservationRepository boatDiscountReservationRepository;
    @Autowired
    MansionDiscountReservationRepository mansionDiscountReservationRepository;
    @Autowired
    AdventureDiscountReservationRepository adventureDiscountReservationRepository;

    public GraphicDataDto examinateAllDataBoatOwner() {
        GraphicDataDto dto = new GraphicDataDto();
        dto.yearly = examinateYearlyDataBoatOwner();
        dto.monthly = examinateMonthlyDataBoatOwner();
        dto.weekly = examinateWeeklyDataBoatOwner();
        if (dto.weekly == null || dto.monthly == null || dto.yearly == null) {
            System.out.println("something is null");
            if(dto.weekly == null){
                System.out.println("week");
            }
            if(dto.monthly == null){
                System.out.println("month");
            }
            if(dto.yearly == null){
                System.out.println("yearly");
            }
            return null;
        }
        return dto;
    }

    public GraphicDataDto examinateAllDataMansionOwner() {
        GraphicDataDto dto = new GraphicDataDto();
        dto.yearly = examinateYearlyDataMansionOwner();
        dto.monthly = examinateMonthlyDataMansionOwner();
        dto.weekly = examinateWeeklyDataMansionOwner();
        if (dto.weekly == null || dto.monthly == null || dto.yearly == null) {
            return null;
        }
        return dto;
    }

    public GraphicDataDto examinateAllDataFishingInstructor() {
        GraphicDataDto dto = new GraphicDataDto();
        dto.yearly = examinateYearlyDataFishingInstructor();
        dto.monthly = examinateMonthlyDataFishingInstructor();
        dto.weekly = examinateWeeklyDataFishingInstructor();
        if (dto.weekly == null || dto.monthly == null || dto.yearly == null) {
            return null;
        }
        return dto;
    }


    private GraphicDataWeeklyOwnerDto examinateWeeklyDataBoatOwner() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BoatOwner boatOwner = boatOwnerRepository.findById(user.getId()).get();
        List<Boat> allBoats = boatRepository.findBoatByBoatOwner(boatOwner);
        Integer monday = 0;
        Integer tuesday = 0;
        Integer wednesday = 0;
        Integer thursday = 0;
        Integer friday = 0;
        Integer saturday = 0;
        Integer sunday = 0;
        Integer mondayi = 0;
        Integer tuesdayi = 0;
        Integer wednesdayi = 0;
        Integer thursdayi = 0;
        Integer fridayi = 0;
        Integer saturdayi = 0;
        Integer sundayi = 0;
        double incomings = 0;
        GraphicDataWeeklyOwnerDto weekly = new GraphicDataWeeklyOwnerDto();
        for (Boat b : allBoats) {
            GraphicDataWeeklyEntityDto dto = calculateWeeklyDataForBoat(b, findAllReservationsByBoat(b));
            if (dto == null) {
                return null;
            }
            monday += dto.entityData.getMonday();
            tuesday += dto.entityData.getTuesday();
            wednesday += dto.entityData.getWednesday();
            thursday += dto.entityData.getThurstady();
            friday += dto.entityData.getFriday();
            saturday += dto.entityData.getSaturday();
            sunday += dto.entityData.getSunday();
            mondayi += dto.entityData.getMondayi();
            tuesdayi += dto.entityData.getTuesdayi();
            wednesdayi += dto.entityData.getWednesdayi();
            thursdayi += dto.entityData.getThurstadyi();
            fridayi += dto.entityData.getFridayi();
            saturdayi += dto.entityData.getSaturdayi();
            sundayi += dto.entityData.getSundayi();
            incomings += dto.entityData.getIncomings();
            weekly.entityData.add(dto);
        }
        weekly.allData.setMonday(monday);
        weekly.allData.setTuesday(tuesday);
        weekly.allData.setWednesday(wednesday);
        weekly.allData.setThurstady(thursday);
        weekly.allData.setFriday(friday);
        weekly.allData.setSaturday(saturday);
        weekly.allData.setSunday(sunday);
        weekly.allData.setMondayi(mondayi);
        weekly.allData.setTuesdayi(tuesdayi);
        weekly.allData.setWednesdayi(wednesdayi);
        weekly.allData.setThurstadyi(thursdayi);
        weekly.allData.setFridayi(fridayi);
        weekly.allData.setSaturdayi(saturdayi);
        weekly.allData.setSundayi(sundayi);
        weekly.allData.setIncomings(incomings);
        return weekly;
    }

    private GraphicDataWeeklyOwnerDto examinateWeeklyDataMansionOwner() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MansionOwner boatOwner = mansionOwnerRepository.findById(user.getId()).get();
        List<Mansion> allBoats = mansionRepository.findAllByMansionOwner(boatOwner);
        Integer monday = 0;
        Integer tuesday = 0;
        Integer wednesday = 0;
        Integer thursday = 0;
        Integer friday = 0;
        Integer saturday = 0;
        Integer sunday = 0;
        Integer mondayi = 0;
        Integer tuesdayi = 0;
        Integer wednesdayi = 0;
        Integer thursdayi = 0;
        Integer fridayi = 0;
        Integer saturdayi = 0;
        Integer sundayi = 0;
        double incomings = 0;
        GraphicDataWeeklyOwnerDto weekly = new GraphicDataWeeklyOwnerDto();
        for (Mansion b : allBoats) {
            GraphicDataWeeklyEntityDto dto = calculateWeeklyDataForBoat(b, findAllReservationsByMansion(b));
            if (dto == null) {
                return null;
            }
            monday += dto.entityData.getMonday();
            tuesday += dto.entityData.getTuesday();
            wednesday += dto.entityData.getWednesday();
            thursday += dto.entityData.getThurstady();
            friday += dto.entityData.getFriday();
            saturday += dto.entityData.getSaturday();
            sunday += dto.entityData.getSunday();
            mondayi += dto.entityData.getMondayi();
            tuesdayi += dto.entityData.getTuesdayi();
            wednesdayi += dto.entityData.getWednesdayi();
            thursdayi += dto.entityData.getThurstadyi();
            fridayi += dto.entityData.getFridayi();
            saturdayi += dto.entityData.getSaturdayi();
            sundayi += dto.entityData.getSundayi();
            incomings += dto.entityData.getIncomings();
            weekly.entityData.add(dto);
        }
        weekly.allData.setMonday(monday);
        weekly.allData.setTuesday(tuesday);
        weekly.allData.setWednesday(wednesday);
        weekly.allData.setThurstady(thursday);
        weekly.allData.setFriday(friday);
        weekly.allData.setSaturday(saturday);
        weekly.allData.setSunday(sunday);
        weekly.allData.setMondayi(mondayi);
        weekly.allData.setTuesdayi(tuesdayi);
        weekly.allData.setWednesdayi(wednesdayi);
        weekly.allData.setThurstadyi(thursdayi);
        weekly.allData.setFridayi(fridayi);
        weekly.allData.setSaturdayi(saturdayi);
        weekly.allData.setSundayi(sundayi);
        weekly.allData.setIncomings(incomings);
        return weekly;
    }

    private GraphicDataWeeklyOwnerDto examinateWeeklyDataFishingInstructor() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FishingInstructor boatOwner = fishingInstructorRepository.findById(user.getId()).get();
        List<Adventure> allBoats = adventureRepository.findAllByFishingInstructor(boatOwner);
        Integer monday = 0;
        Integer tuesday = 0;
        Integer wednesday = 0;
        Integer thursday = 0;
        Integer friday = 0;
        Integer saturday = 0;
        Integer sunday = 0;
        Integer mondayi = 0;
        Integer tuesdayi = 0;
        Integer wednesdayi = 0;
        Integer thursdayi = 0;
        Integer fridayi = 0;
        Integer saturdayi = 0;
        Integer sundayi = 0;
        double incomings = 0;
        GraphicDataWeeklyOwnerDto weekly = new GraphicDataWeeklyOwnerDto();
        for (Adventure b : allBoats) {
            GraphicDataWeeklyEntityDto dto = calculateWeeklyDataForBoat(b, findAllReservationsByAdventure(b));
            if (dto == null) {
                return null;
            }
            monday += dto.entityData.getMonday();
            tuesday += dto.entityData.getTuesday();
            wednesday += dto.entityData.getWednesday();
            thursday += dto.entityData.getThurstady();
            friday += dto.entityData.getFriday();
            saturday += dto.entityData.getSaturday();
            sunday += dto.entityData.getSunday();
            mondayi += dto.entityData.getMondayi();
            tuesdayi += dto.entityData.getTuesdayi();
            wednesdayi += dto.entityData.getWednesdayi();
            thursdayi += dto.entityData.getThurstadyi();
            fridayi += dto.entityData.getFridayi();
            saturdayi += dto.entityData.getSaturdayi();
            sundayi += dto.entityData.getSundayi();
            incomings += dto.entityData.getIncomings();
            weekly.entityData.add(dto);
        }
        weekly.allData.setMonday(monday);
        weekly.allData.setTuesday(tuesday);
        weekly.allData.setWednesday(wednesday);
        weekly.allData.setThurstady(thursday);
        weekly.allData.setFriday(friday);
        weekly.allData.setSaturday(saturday);
        weekly.allData.setSunday(sunday);
        weekly.allData.setMondayi(mondayi);
        weekly.allData.setTuesdayi(tuesdayi);
        weekly.allData.setWednesdayi(wednesdayi);
        weekly.allData.setThurstadyi(thursdayi);
        weekly.allData.setFridayi(fridayi);
        weekly.allData.setSaturdayi(saturdayi);
        weekly.allData.setSundayi(sundayi);
        weekly.allData.setIncomings(incomings);
        return weekly;
    }

    private GraphicDataWeeklyEntityDto calculateWeeklyDataForBoat(AbstractEntity b, List<AbstractReservation> regulaReservations) {
        GraphicDataWeeklyEntityDto dto = new GraphicDataWeeklyEntityDto(b.getId(), b.getName());
        dto.entityData = new GraphicDataWeekly();
        LocalDateTime now = calculateNow();
        LocalDateTime startWeek = calculateMonday(now);
        LocalDateTime startWeekOr = startWeek;
        LocalDateTime endWeek = startWeek.plusDays(7);
        now = startWeek;
        dto.entityData.setIncomings(dto.entityData.getIncomings() + calculateIncomingsForReservations(regulaReservations, startWeekOr, endWeek));
        while (startWeek.isBefore(endWeek)) {
            for (AbstractReservation br : regulaReservations) {
                if (startWeek.isAfter(DateCoverter.convertToLocalDateTimeViaInstant(br.getStartDate())) && startWeek.isBefore(DateCoverter.convertToLocalDateTimeViaInstant(br.getEndDate()))
                        && br.getStatus() != ReservationStatus.CANCELLED) {
                    if (now.getDayOfWeek() == DayOfWeek.MONDAY)
                        dto.entityData.setMonday(dto.entityData.getMonday() + 1);
                    else if (now.getDayOfWeek() == DayOfWeek.TUESDAY)
                        dto.entityData.setTuesday(dto.entityData.getTuesday() + 1);
                    else if (now.getDayOfWeek() == DayOfWeek.WEDNESDAY)
                        dto.entityData.setWednesday(dto.entityData.getWednesday() + 1);
                    else if (now.getDayOfWeek() == DayOfWeek.THURSDAY)
                        dto.entityData.setThurstady(dto.entityData.getThurstady() + 1);
                    else if (now.getDayOfWeek() == DayOfWeek.FRIDAY)
                        dto.entityData.setFriday(dto.entityData.getFriday() + 1);
                    else if (now.getDayOfWeek() == DayOfWeek.SATURDAY)
                        dto.entityData.setSaturday(dto.entityData.getSaturday() + 1);
                    else if (now.getDayOfWeek() == DayOfWeek.SUNDAY)
                        dto.entityData.setSunday(dto.entityData.getSunday() + 1);
                    else return null;
                }
                if (startWeek.toLocalDate().equals(DateCoverter.convertToLocalDateTimeViaInstant(br.getStartDate()).toLocalDate())){
                    if (now.getDayOfWeek() == DayOfWeek.MONDAY)
                        dto.entityData.setMondayi((int) (dto.entityData.getMondayi() + br.getTotalPrice()));
                    else if (now.getDayOfWeek() == DayOfWeek.TUESDAY)
                        dto.entityData.setTuesdayi((int) (dto.entityData.getTuesdayi() + br.getTotalPrice()));
                    else if (now.getDayOfWeek() == DayOfWeek.WEDNESDAY)
                        dto.entityData.setWednesdayi((int) (dto.entityData.getWednesdayi() + br.getTotalPrice()));
                    else if (now.getDayOfWeek() == DayOfWeek.THURSDAY)
                        dto.entityData.setThurstadyi((int) (dto.entityData.getThurstadyi() + br.getTotalPrice()));
                    else if (now.getDayOfWeek() == DayOfWeek.FRIDAY)
                        dto.entityData.setFridayi((int) (dto.entityData.getFridayi() + br.getTotalPrice()));
                    else if (now.getDayOfWeek() == DayOfWeek.SATURDAY)
                        dto.entityData.setSaturdayi((int) (dto.entityData.getSaturdayi() + br.getTotalPrice()));
                    else if (now.getDayOfWeek() == DayOfWeek.SUNDAY)
                        dto.entityData.setSundayi((int) (dto.entityData.getSundayi() + br.getTotalPrice()));
                    else return null;
                }
            }
            startWeek = startWeek.plusDays(1);
            now = startWeek;
        }
        return dto;
    }

    private LocalDateTime calculateNow() {
        LocalDateTime now = LocalDateTime.now();
        now = now.toLocalDate().atTime(0, 0);
        return now;
    }

    private LocalDateTime calculateMonday(LocalDateTime now) {
        LocalDateTime startWeek = null;
        if (now.getDayOfWeek() == DayOfWeek.MONDAY) startWeek = LocalDateTime.now();
        else if (now.getDayOfWeek() == DayOfWeek.TUESDAY) startWeek = LocalDateTime.now().minusDays(1);
        else if (now.getDayOfWeek() == DayOfWeek.WEDNESDAY) startWeek = LocalDateTime.now().minusDays(2);
        else if (now.getDayOfWeek() == DayOfWeek.THURSDAY) startWeek = LocalDateTime.now().minusDays(3);
        else if (now.getDayOfWeek() == DayOfWeek.FRIDAY) startWeek = LocalDateTime.now().minusDays(4);
        else if (now.getDayOfWeek() == DayOfWeek.SATURDAY) startWeek = LocalDateTime.now().minusDays(5);
        else if (now.getDayOfWeek() == DayOfWeek.SUNDAY) startWeek = LocalDateTime.now().minusDays(6);
        else return null;
        return startWeek;
    }

    private double calculateIncomingsForReservations(List<AbstractReservation> regulaReservations, LocalDateTime startWeekOr, LocalDateTime endWeek) {
        double incomings = 0;
        for (AbstractReservation br : regulaReservations) {
            LocalDateTime resStartDate = DateCoverter.convertToLocalDateTimeViaInstant(br.getStartDate());
            if (resStartDate.isAfter(startWeekOr) && resStartDate.isBefore(endWeek)
                    && br.getStatus() != ReservationStatus.CANCELLED
                    && br.getStatus() != ReservationStatus.DELETED
                    && br.getUser() != null) {
                incomings += br.getTotalPrice();
            }
        }
        return incomings;
    }

    private GraphicDataMonthlyOwnerDto examinateMonthlyDataBoatOwner() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BoatOwner boatOwner = boatOwnerRepository.findById(user.getId()).get();
        List<Boat> allBoats = boatRepository.findBoatByBoatOwner(boatOwner);
        Integer one = 0;
        Integer two = 0;
        Integer three = 0;
        Integer four = 0;
        Integer five = 0;
        Integer six = 0;
        Integer seven = 0;
        Integer eight = 0;
        Integer nine = 0;
        Integer ten = 0;
        Integer eleven = 0;
        Integer twelve = 0;
        Integer thirteen = 0;
        Integer fourteen = 0;
        Integer fifteen = 0;
        Integer sixteen = 0;
        Integer seventeen = 0;
        Integer eighteen = 0;
        Integer nineteen = 0;
        Integer twenty = 0;
        Integer twentyOne = 0;
        Integer twentyTwo = 0;
        Integer twentyThree = 0;
        Integer twentyFour = 0;
        Integer twentyFive = 0;
        Integer twentySix = 0;
        Integer twentySeven = 0;
        Integer twentyEight = 0;
        Integer twentyNine = 0;
        Integer thirty = 0;
        Integer thirtyOne = 0;
        Integer onei = 0;
        Integer twoi = 0;
        Integer threei = 0;
        Integer fouri = 0;
        Integer fivei = 0;
        Integer sixi = 0;
        Integer seveni = 0;
        Integer eighti = 0;
        Integer ninei = 0;
        Integer teni = 0;
        Integer eleveni = 0;
        Integer twelvei = 0;
        Integer thirteeni = 0;
        Integer fourteeni = 0;
        Integer fifteeni = 0;
        Integer sixteeni = 0;
        Integer seventeeni = 0;
        Integer eighteeni = 0;
        Integer nineteeni = 0;
        Integer twentyi = 0;
        Integer twentyOnei = 0;
        Integer twentyTwoi = 0;
        Integer twentyThreei = 0;
        Integer twentyFouri = 0;
        Integer twentyFivei = 0;
        Integer twentySixi = 0;
        Integer twentySeveni = 0;
        Integer twentyEighti = 0;
        Integer twentyNinei = 0;
        Integer thirtyi = 0;
        Integer thirtyOnei = 0;
        double incomings = 0;
        GraphicDataMonthlyOwnerDto monthly = new GraphicDataMonthlyOwnerDto();
        for (Boat b : allBoats) {
            GraphicDataMonthlyEntityDto dto = calculateMonthlyDataForBoat(b, findAllReservationsByBoat(b));
            if (dto == null) {
                System.out.println("Return dto is null");
                return null;
            }
            one += dto.entityData.getOne();
            two += dto.entityData.getTwo();
            three += dto.entityData.getThree();
            four += dto.entityData.getFour();
            five += dto.entityData.getFive();
            six += dto.entityData.getSix();
            seven += dto.entityData.getSeven();
            eight += dto.entityData.getEight();
            nine += dto.entityData.getNine();
            ten += dto.entityData.getTen();
            eleven += dto.entityData.getEleven();
            twelve += dto.entityData.getTwelve();
            thirteen += dto.entityData.getThirteen();
            fourteen += dto.entityData.getFourteen();
            fifteen += dto.entityData.getFifteen();
            sixteen += dto.entityData.getSixteen();
            seventeen += dto.entityData.getSeventeen();
            eighteen += dto.entityData.getEighteen();
            nineteen += dto.entityData.getNineteen();
            twenty += dto.entityData.getTwenty();
            twentyOne += dto.entityData.getTwentyOne();
            twentyTwo += dto.entityData.getTwentyTwo();
            twentyThree += dto.entityData.getTwentyThree();
            twentyFour += dto.entityData.getTwentyFour();
            twentyFive += dto.entityData.getTwentyFive();
            twentySix += dto.entityData.getTwentySix();
            twentySeven += dto.entityData.getTwentySeven();
            twentyEight += dto.entityData.getTwentyEight();
            twentyNine += dto.entityData.getTwentyNine();
            thirty += dto.entityData.getThirty();
            thirtyOne += dto.entityData.getThirtyOne();
            onei += dto.entityData.getOnei();
            twoi += dto.entityData.getTwo();
            threei += dto.entityData.getThreei();
            fouri += dto.entityData.getFouri();
            fivei += dto.entityData.getFivei();
            sixi += dto.entityData.getSixi();
            seveni += dto.entityData.getSeveni();
            eighti += dto.entityData.getEighti();
            ninei += dto.entityData.getNinei();
            teni += dto.entityData.getTeni();
            eleveni += dto.entityData.getEleveni();
            twelvei += dto.entityData.getTwelvei();
            thirteeni += dto.entityData.getThirteeni();
            fourteeni += dto.entityData.getFourteeni();
            fifteeni += dto.entityData.getFifteeni();
            sixteeni += dto.entityData.getSixteeni();
            seventeeni += dto.entityData.getSeventeeni();
            eighteeni += dto.entityData.getEighteeni();
            nineteeni += dto.entityData.getNineteeni();
            twentyi += dto.entityData.getTwentyi();
            twentyOnei += dto.entityData.getTwentyOnei();
            twentyTwoi += dto.entityData.getTwentyTwoi();
            twentyThreei += dto.entityData.getTwentyThreei();
            twentyFouri += dto.entityData.getTwentyFouri();
            twentyFivei += dto.entityData.getTwentyFivei();
            twentySixi += dto.entityData.getTwentySixi();
            twentySeveni += dto.entityData.getTwentySeveni();
            twentyEighti += dto.entityData.getTwentyEighti();
            twentyNinei += dto.entityData.getTwentyNinei();
            thirtyi += dto.entityData.getThirtyi();
            thirtyOnei += dto.entityData.getThirtyOnei();
            incomings += dto.entityData.getIncomings();
            monthly.entityData.add(dto);
        }
        monthly.allData.setOne(one);
        monthly.allData.setTwo(two);
        monthly.allData.setThree(three);
        monthly.allData.setFour(four);
        monthly.allData.setFive(five);
        monthly.allData.setSix(six);
        monthly.allData.setSeven(seven);
        monthly.allData.setEight(eight);
        monthly.allData.setNine(nine);
        monthly.allData.setTen(ten);
        monthly.allData.setEleven(eleven);
        monthly.allData.setTwelve(twelve);
        monthly.allData.setThirteen(thirteen);
        monthly.allData.setFourteen(fourteen);
        monthly.allData.setFifteen(fifteen);
        monthly.allData.setSixteen(sixteen);
        monthly.allData.setSeventeen(seventeen);
        monthly.allData.setEighteen(eighteen);
        monthly.allData.setNineteen(nineteen);
        monthly.allData.setTwenty(twenty);
        monthly.allData.setTwentyOne(twentyOne);
        monthly.allData.setTwentyTwo(twentyTwo);
        monthly.allData.setTwentyThree(twentyThree);
        monthly.allData.setTwentyFour(twentyFour);
        monthly.allData.setTwentyFive(twentyFive);
        monthly.allData.setTwentySix(twentySix);
        monthly.allData.setTwentySeven(twentySeven);
        monthly.allData.setTwentyEight(twentyEight);
        monthly.allData.setTwentyNine(twentyNine);
        monthly.allData.setThirty(thirty);
        monthly.allData.setThirtyOne(thirtyOne);
        monthly.allData.setOnei(onei);
        monthly.allData.setTwoi(twoi);
        monthly.allData.setThreei(threei);
        monthly.allData.setFouri(fouri);
        monthly.allData.setFivei(fivei);
        monthly.allData.setSixi(sixi);
        monthly.allData.setSeveni(seveni);
        monthly.allData.setEighti(eighti);
        monthly.allData.setNinei(ninei);
        monthly.allData.setTeni(teni);
        monthly.allData.setEleveni(eleveni);
        monthly.allData.setTwelvei(twelvei);
        monthly.allData.setThirteeni(thirteeni);
        monthly.allData.setFourteeni(fourteeni);
        monthly.allData.setFifteeni(fifteeni);
        monthly.allData.setSixteeni(sixteeni);
        monthly.allData.setSeventeeni(seventeeni);
        monthly.allData.setEighteeni(eighteeni);
        monthly.allData.setNineteeni(nineteeni);
        monthly.allData.setTwentyi(twentyi);
        monthly.allData.setTwentyOnei(twentyOnei);
        monthly.allData.setTwentyTwoi(twentyTwoi);
        monthly.allData.setTwentyThreei(twentyThreei);
        monthly.allData.setTwentyFouri(twentyFouri);
        monthly.allData.setTwentyFivei(twentyFivei);
        monthly.allData.setTwentySixi(twentySixi);
        monthly.allData.setTwentySeveni(twentySeveni);
        monthly.allData.setTwentyEighti(twentyEighti);
        monthly.allData.setTwentyNinei(twentyNinei);
        monthly.allData.setThirtyi(thirtyi);
        monthly.allData.setThirtyOnei(thirtyOnei);
        monthly.allData.setIncomings(incomings);
        return monthly;

    }

    private GraphicDataMonthlyOwnerDto examinateMonthlyDataMansionOwner() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MansionOwner boatOwner = mansionOwnerRepository.findById(user.getId()).get();
        List<Mansion> allBoats = mansionRepository.findAllByMansionOwner(boatOwner);
        Integer one = 0;
        Integer two = 0;
        Integer three = 0;
        Integer four = 0;
        Integer five = 0;
        Integer six = 0;
        Integer seven = 0;
        Integer eight = 0;
        Integer nine = 0;
        Integer ten = 0;
        Integer eleven = 0;
        Integer twelve = 0;
        Integer thirteen = 0;
        Integer fourteen = 0;
        Integer fifteen = 0;
        Integer sixteen = 0;
        Integer seventeen = 0;
        Integer eighteen = 0;
        Integer nineteen = 0;
        Integer twenty = 0;
        Integer twentyOne = 0;
        Integer twentyTwo = 0;
        Integer twentyThree = 0;
        Integer twentyFour = 0;
        Integer twentyFive = 0;
        Integer twentySix = 0;
        Integer twentySeven = 0;
        Integer twentyEight = 0;
        Integer twentyNine = 0;
        Integer thirty = 0;
        Integer thirtyOne = 0;
        Integer onei = 0;
        Integer twoi = 0;
        Integer threei = 0;
        Integer fouri = 0;
        Integer fivei = 0;
        Integer sixi = 0;
        Integer seveni = 0;
        Integer eighti = 0;
        Integer ninei = 0;
        Integer teni = 0;
        Integer eleveni = 0;
        Integer twelvei = 0;
        Integer thirteeni = 0;
        Integer fourteeni = 0;
        Integer fifteeni = 0;
        Integer sixteeni = 0;
        Integer seventeeni = 0;
        Integer eighteeni = 0;
        Integer nineteeni = 0;
        Integer twentyi = 0;
        Integer twentyOnei = 0;
        Integer twentyTwoi = 0;
        Integer twentyThreei = 0;
        Integer twentyFouri = 0;
        Integer twentyFivei = 0;
        Integer twentySixi = 0;
        Integer twentySeveni = 0;
        Integer twentyEighti = 0;
        Integer twentyNinei = 0;
        Integer thirtyi = 0;
        Integer thirtyOnei = 0;
        double incomings = 0;
        GraphicDataMonthlyOwnerDto monthly = new GraphicDataMonthlyOwnerDto();
        for (Mansion b : allBoats) {
            GraphicDataMonthlyEntityDto dto = calculateMonthlyDataForBoat(b, findAllReservationsByMansion(b));
            if (dto == null) return null;
            one += dto.entityData.getOne();
            two += dto.entityData.getTwo();
            three += dto.entityData.getThree();
            four += dto.entityData.getFour();
            five += dto.entityData.getFive();
            six += dto.entityData.getSix();
            seven += dto.entityData.getSeven();
            eight += dto.entityData.getEight();
            nine += dto.entityData.getNine();
            ten += dto.entityData.getTen();
            eleven += dto.entityData.getEleven();
            twelve += dto.entityData.getTwelve();
            thirteen += dto.entityData.getThirteen();
            fourteen += dto.entityData.getFourteen();
            fifteen += dto.entityData.getFifteen();
            sixteen += dto.entityData.getSixteen();
            seventeen += dto.entityData.getSeventeen();
            eighteen += dto.entityData.getEighteen();
            nineteen += dto.entityData.getNineteen();
            twenty += dto.entityData.getTwenty();
            twentyOne += dto.entityData.getTwentyOne();
            twentyTwo += dto.entityData.getTwentyTwo();
            twentyThree += dto.entityData.getTwentyThree();
            twentyFour += dto.entityData.getTwentyFour();
            twentyFive += dto.entityData.getTwentyFive();
            twentySix += dto.entityData.getTwentySix();
            twentySeven += dto.entityData.getTwentySeven();
            twentyEight += dto.entityData.getTwentyEight();
            twentyNine += dto.entityData.getTwentyNine();
            thirty += dto.entityData.getThirty();
            thirtyOne += dto.entityData.getThirtyOne();
            onei += dto.entityData.getOnei();
            twoi += dto.entityData.getTwo();
            threei += dto.entityData.getThreei();
            fouri += dto.entityData.getFouri();
            fivei += dto.entityData.getFivei();
            sixi += dto.entityData.getSixi();
            seveni += dto.entityData.getSeveni();
            eighti += dto.entityData.getEighti();
            ninei += dto.entityData.getNinei();
            teni += dto.entityData.getTeni();
            eleveni += dto.entityData.getEleveni();
            twelvei += dto.entityData.getTwelvei();
            thirteeni += dto.entityData.getThirteeni();
            fourteeni += dto.entityData.getFourteeni();
            fifteeni += dto.entityData.getFifteeni();
            sixteeni += dto.entityData.getSixteeni();
            seventeeni += dto.entityData.getSeventeeni();
            eighteeni += dto.entityData.getEighteeni();
            nineteeni += dto.entityData.getNineteeni();
            twentyi += dto.entityData.getTwentyi();
            twentyOnei += dto.entityData.getTwentyOnei();
            twentyTwoi += dto.entityData.getTwentyTwoi();
            twentyThreei += dto.entityData.getTwentyThreei();
            twentyFouri += dto.entityData.getTwentyFouri();
            twentyFivei += dto.entityData.getTwentyFivei();
            twentySixi += dto.entityData.getTwentySixi();
            twentySeveni += dto.entityData.getTwentySeveni();
            twentyEighti += dto.entityData.getTwentyEighti();
            twentyNinei += dto.entityData.getTwentyNinei();
            thirtyi += dto.entityData.getThirtyi();
            thirtyOnei += dto.entityData.getThirtyOnei();
            incomings += dto.entityData.getIncomings();
            monthly.entityData.add(dto);
        }
        monthly.allData.setOne(one);
        monthly.allData.setTwo(two);
        monthly.allData.setThree(three);
        monthly.allData.setFour(four);
        monthly.allData.setFive(five);
        monthly.allData.setSix(six);
        monthly.allData.setSeven(seven);
        monthly.allData.setEight(eight);
        monthly.allData.setNine(nine);
        monthly.allData.setTen(ten);
        monthly.allData.setEleven(eleven);
        monthly.allData.setTwelve(twelve);
        monthly.allData.setThirteen(thirteen);
        monthly.allData.setFourteen(fourteen);
        monthly.allData.setFifteen(fifteen);
        monthly.allData.setSixteen(sixteen);
        monthly.allData.setSeventeen(seventeen);
        monthly.allData.setEighteen(eighteen);
        monthly.allData.setNineteen(nineteen);
        monthly.allData.setTwenty(twenty);
        monthly.allData.setTwentyOne(twentyOne);
        monthly.allData.setTwentyTwo(twentyTwo);
        monthly.allData.setTwentyThree(twentyThree);
        monthly.allData.setTwentyFour(twentyFour);
        monthly.allData.setTwentyFive(twentyFive);
        monthly.allData.setTwentySix(twentySix);
        monthly.allData.setTwentySeven(twentySeven);
        monthly.allData.setTwentyEight(twentyEight);
        monthly.allData.setTwentyNine(twentyNine);
        monthly.allData.setThirty(thirty);
        monthly.allData.setThirtyOne(thirtyOne);
        monthly.allData.setOnei(onei);
        monthly.allData.setTwoi(twoi);
        monthly.allData.setThreei(threei);
        monthly.allData.setFouri(fouri);
        monthly.allData.setFivei(fivei);
        monthly.allData.setSixi(sixi);
        monthly.allData.setSeveni(seveni);
        monthly.allData.setEighti(eighti);
        monthly.allData.setNinei(ninei);
        monthly.allData.setTeni(teni);
        monthly.allData.setEleveni(eleveni);
        monthly.allData.setTwelvei(twelvei);
        monthly.allData.setThirteeni(thirteeni);
        monthly.allData.setFourteeni(fourteeni);
        monthly.allData.setFifteeni(fifteeni);
        monthly.allData.setSixteeni(sixteeni);
        monthly.allData.setSeventeeni(seventeeni);
        monthly.allData.setEighteeni(eighteeni);
        monthly.allData.setNineteeni(nineteeni);
        monthly.allData.setTwentyi(twentyi);
        monthly.allData.setTwentyOnei(twentyOnei);
        monthly.allData.setTwentyTwoi(twentyTwoi);
        monthly.allData.setTwentyThreei(twentyThreei);
        monthly.allData.setTwentyFouri(twentyFouri);
        monthly.allData.setTwentyFivei(twentyFivei);
        monthly.allData.setTwentySixi(twentySixi);
        monthly.allData.setTwentySeveni(twentySeveni);
        monthly.allData.setTwentyEighti(twentyEighti);
        monthly.allData.setTwentyNinei(twentyNinei);
        monthly.allData.setThirtyi(thirtyi);
        monthly.allData.setThirtyOnei(thirtyOnei);
        monthly.allData.setIncomings(incomings);
        return monthly;

    }

    private GraphicDataMonthlyOwnerDto examinateMonthlyDataFishingInstructor() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FishingInstructor boatOwner = fishingInstructorRepository.findById(user.getId()).get();
        List<Adventure> allBoats = adventureRepository.findAllByFishingInstructor(boatOwner);
        Integer one = 0;
        Integer two = 0;
        Integer three = 0;
        Integer four = 0;
        Integer five = 0;
        Integer six = 0;
        Integer seven = 0;
        Integer eight = 0;
        Integer nine = 0;
        Integer ten = 0;
        Integer eleven = 0;
        Integer twelve = 0;
        Integer thirteen = 0;
        Integer fourteen = 0;
        Integer fifteen = 0;
        Integer sixteen = 0;
        Integer seventeen = 0;
        Integer eighteen = 0;
        Integer nineteen = 0;
        Integer twenty = 0;
        Integer twentyOne = 0;
        Integer twentyTwo = 0;
        Integer twentyThree = 0;
        Integer twentyFour = 0;
        Integer twentyFive = 0;
        Integer twentySix = 0;
        Integer twentySeven = 0;
        Integer twentyEight = 0;
        Integer twentyNine = 0;
        Integer thirty = 0;
        Integer thirtyOne = 0;
        Integer onei = 0;
        Integer twoi = 0;
        Integer threei = 0;
        Integer fouri = 0;
        Integer fivei = 0;
        Integer sixi = 0;
        Integer seveni = 0;
        Integer eighti = 0;
        Integer ninei = 0;
        Integer teni = 0;
        Integer eleveni = 0;
        Integer twelvei = 0;
        Integer thirteeni = 0;
        Integer fourteeni = 0;
        Integer fifteeni = 0;
        Integer sixteeni = 0;
        Integer seventeeni = 0;
        Integer eighteeni = 0;
        Integer nineteeni = 0;
        Integer twentyi = 0;
        Integer twentyOnei = 0;
        Integer twentyTwoi = 0;
        Integer twentyThreei = 0;
        Integer twentyFouri = 0;
        Integer twentyFivei = 0;
        Integer twentySixi = 0;
        Integer twentySeveni = 0;
        Integer twentyEighti = 0;
        Integer twentyNinei = 0;
        Integer thirtyi = 0;
        Integer thirtyOnei = 0;
        double incomings = 0;
        GraphicDataMonthlyOwnerDto monthly = new GraphicDataMonthlyOwnerDto();
        for (Adventure b : allBoats) {
            GraphicDataMonthlyEntityDto dto = calculateMonthlyDataForBoat(b, findAllReservationsByAdventure(b));
            if (dto == null) return null;
            one += dto.entityData.getOne();
            two += dto.entityData.getTwo();
            three += dto.entityData.getThree();
            four += dto.entityData.getFour();
            five += dto.entityData.getFive();
            six += dto.entityData.getSix();
            seven += dto.entityData.getSeven();
            eight += dto.entityData.getEight();
            nine += dto.entityData.getNine();
            ten += dto.entityData.getTen();
            eleven += dto.entityData.getEleven();
            twelve += dto.entityData.getTwelve();
            thirteen += dto.entityData.getThirteen();
            fourteen += dto.entityData.getFourteen();
            fifteen += dto.entityData.getFifteen();
            sixteen += dto.entityData.getSixteen();
            seventeen += dto.entityData.getSeventeen();
            eighteen += dto.entityData.getEighteen();
            nineteen += dto.entityData.getNineteen();
            twenty += dto.entityData.getTwenty();
            twentyOne += dto.entityData.getTwentyOne();
            twentyTwo += dto.entityData.getTwentyTwo();
            twentyThree += dto.entityData.getTwentyThree();
            twentyFour += dto.entityData.getTwentyFour();
            twentyFive += dto.entityData.getTwentyFive();
            twentySix += dto.entityData.getTwentySix();
            twentySeven += dto.entityData.getTwentySeven();
            twentyEight += dto.entityData.getTwentyEight();
            twentyNine += dto.entityData.getTwentyNine();
            thirty += dto.entityData.getThirty();
            thirtyOne += dto.entityData.getThirtyOne();
            onei += dto.entityData.getOnei();
            twoi += dto.entityData.getTwo();
            threei += dto.entityData.getThreei();
            fouri += dto.entityData.getFouri();
            fivei += dto.entityData.getFivei();
            sixi += dto.entityData.getSixi();
            seveni += dto.entityData.getSeveni();
            eighti += dto.entityData.getEighti();
            ninei += dto.entityData.getNinei();
            teni += dto.entityData.getTeni();
            eleveni += dto.entityData.getEleveni();
            twelvei += dto.entityData.getTwelvei();
            thirteeni += dto.entityData.getThirteeni();
            fourteeni += dto.entityData.getFourteeni();
            fifteeni += dto.entityData.getFifteeni();
            sixteeni += dto.entityData.getSixteeni();
            seventeeni += dto.entityData.getSeventeeni();
            eighteeni += dto.entityData.getEighteeni();
            nineteeni += dto.entityData.getNineteeni();
            twentyi += dto.entityData.getTwentyi();
            twentyOnei += dto.entityData.getTwentyOnei();
            twentyTwoi += dto.entityData.getTwentyTwoi();
            twentyThreei += dto.entityData.getTwentyThreei();
            twentyFouri += dto.entityData.getTwentyFouri();
            twentyFivei += dto.entityData.getTwentyFivei();
            twentySixi += dto.entityData.getTwentySixi();
            twentySeveni += dto.entityData.getTwentySeveni();
            twentyEighti += dto.entityData.getTwentyEighti();
            twentyNinei += dto.entityData.getTwentyNinei();
            thirtyi += dto.entityData.getThirtyi();
            thirtyOnei += dto.entityData.getThirtyOnei();
            incomings += dto.entityData.getIncomings();
            monthly.entityData.add(dto);
        }
        monthly.allData.setOne(one);
        monthly.allData.setTwo(two);
        monthly.allData.setThree(three);
        monthly.allData.setFour(four);
        monthly.allData.setFive(five);
        monthly.allData.setSix(six);
        monthly.allData.setSeven(seven);
        monthly.allData.setEight(eight);
        monthly.allData.setNine(nine);
        monthly.allData.setTen(ten);
        monthly.allData.setEleven(eleven);
        monthly.allData.setTwelve(twelve);
        monthly.allData.setThirteen(thirteen);
        monthly.allData.setFourteen(fourteen);
        monthly.allData.setFifteen(fifteen);
        monthly.allData.setSixteen(sixteen);
        monthly.allData.setSeventeen(seventeen);
        monthly.allData.setEighteen(eighteen);
        monthly.allData.setNineteen(nineteen);
        monthly.allData.setTwenty(twenty);
        monthly.allData.setTwentyOne(twentyOne);
        monthly.allData.setTwentyTwo(twentyTwo);
        monthly.allData.setTwentyThree(twentyThree);
        monthly.allData.setTwentyFour(twentyFour);
        monthly.allData.setTwentyFive(twentyFive);
        monthly.allData.setTwentySix(twentySix);
        monthly.allData.setTwentySeven(twentySeven);
        monthly.allData.setTwentyEight(twentyEight);
        monthly.allData.setTwentyNine(twentyNine);
        monthly.allData.setThirty(thirty);
        monthly.allData.setThirtyOne(thirtyOne);
        monthly.allData.setOnei(onei);
        monthly.allData.setTwoi(twoi);
        monthly.allData.setThreei(threei);
        monthly.allData.setFouri(fouri);
        monthly.allData.setFivei(fivei);
        monthly.allData.setSixi(sixi);
        monthly.allData.setSeveni(seveni);
        monthly.allData.setEighti(eighti);
        monthly.allData.setNinei(ninei);
        monthly.allData.setTeni(teni);
        monthly.allData.setEleveni(eleveni);
        monthly.allData.setTwelvei(twelvei);
        monthly.allData.setThirteeni(thirteeni);
        monthly.allData.setFourteeni(fourteeni);
        monthly.allData.setFifteeni(fifteeni);
        monthly.allData.setSixteeni(sixteeni);
        monthly.allData.setSeventeeni(seventeeni);
        monthly.allData.setEighteeni(eighteeni);
        monthly.allData.setNineteeni(nineteeni);
        monthly.allData.setTwentyi(twentyi);
        monthly.allData.setTwentyOnei(twentyOnei);
        monthly.allData.setTwentyTwoi(twentyTwoi);
        monthly.allData.setTwentyThreei(twentyThreei);
        monthly.allData.setTwentyFouri(twentyFouri);
        monthly.allData.setTwentyFivei(twentyFivei);
        monthly.allData.setTwentySixi(twentySixi);
        monthly.allData.setTwentySeveni(twentySeveni);
        monthly.allData.setTwentyEighti(twentyEighti);
        monthly.allData.setTwentyNinei(twentyNinei);
        monthly.allData.setThirtyi(thirtyi);
        monthly.allData.setThirtyOnei(thirtyOnei);
        monthly.allData.setIncomings(incomings);
        return monthly;

    }

    private GraphicDataMonthlyEntityDto calculateMonthlyDataForBoat(AbstractEntity b, List<AbstractReservation> regulaReservations) {
        GraphicDataMonthlyEntityDto dto = new GraphicDataMonthlyEntityDto(b.getId(), b.getName());
        dto.entityData = new GraphicDataMonthly();
        for (AbstractReservation br : regulaReservations) {
            if ((br.getStartDate().getYear() + 1900) == LocalDateTime.now().getYear() && ((br.getStartDate().getMonth() + 1) == LocalDateTime.now().getMonthValue()
                    || (br.getEndDate().getMonth() + 1) == LocalDateTime.now().getMonthValue()) && br.getStatus() != ReservationStatus.CANCELLED
                    && br.getUser() != null) {
                dto.entityData.setIncomings(dto.entityData.getIncomings() + br.getTotalPrice());
                LocalDateTime start = DateCoverter.convertToLocalDateTimeViaInstant(br.getStartDate());
                LocalDateTime end = DateCoverter.convertToLocalDateTimeViaInstant(br.getEndDate());
                start = start.toLocalDate().atTime(0, 0);
                while (start.isBefore(end)) {
                    if (start.getMonthValue() == LocalDateTime.now().getMonthValue()) {
                        if (start.getDayOfMonth() == 1) {
                            dto.entityData.setOne(dto.entityData.getOne() + 1);
                            dto.entityData.setOnei((int) (dto.entityData.getOnei() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 2) {
                            dto.entityData.setTwo(dto.entityData.getTwo() + 1);
                            dto.entityData.setTwoi((int) (dto.entityData.getTwoi() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 3) {
                            dto.entityData.setThree(dto.entityData.getThree() + 1);
                            dto.entityData.setThreei((int) (dto.entityData.getThreei() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 4) {
                            dto.entityData.setFour(dto.entityData.getFour() + 1);
                            dto.entityData.setFouri((int) (dto.entityData.getFouri() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 5) {
                            dto.entityData.setFive(dto.entityData.getFive() + 1);
                            dto.entityData.setFivei((int) (dto.entityData.getFivei() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 6) {
                            dto.entityData.setSix(dto.entityData.getSix() + 1);
                            dto.entityData.setSixi((int) (dto.entityData.getSixi() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 7) {
                            dto.entityData.setSeven(dto.entityData.getSeven() + 1);
                            dto.entityData.setSeveni((int) (dto.entityData.getSeveni() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 8) {
                            dto.entityData.setEight(dto.entityData.getEight() + 1);
                            dto.entityData.setEighti((int) (dto.entityData.getEighti() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 9) {
                            dto.entityData.setNine(dto.entityData.getNine() + 1);
                            dto.entityData.setNinei((int) (dto.entityData.getNinei() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 10) {
                            dto.entityData.setTen(dto.entityData.getTen() + 1);
                            dto.entityData.setTeni((int) (dto.entityData.getTeni() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 11) {
                            dto.entityData.setEleven(dto.entityData.getEleven() + 1);
                            dto.entityData.setEleveni((int) (dto.entityData.getEleveni() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 12) {
                            dto.entityData.setTwelve(dto.entityData.getTwelve() + 1);
                            dto.entityData.setTwelvei((int) (dto.entityData.getTwelvei() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 13) {
                            dto.entityData.setThirteen(dto.entityData.getThirteen() + 1);
                            dto.entityData.setThirteeni((int) (dto.entityData.getThirteeni() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 14) {
                            dto.entityData.setFourteen(dto.entityData.getFourteen() + 1);
                            dto.entityData.setFourteeni((int) (dto.entityData.getFourteeni() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 15) {
                            dto.entityData.setFifteen(dto.entityData.getFifteen() + 1);
                            dto.entityData.setFifteeni((int) (dto.entityData.getFifteeni() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 16) {
                            dto.entityData.setSixteen(dto.entityData.getSixteen() + 1);
                            dto.entityData.setSixteeni((int) (dto.entityData.getSixteeni() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 17) {
                            dto.entityData.setSeventeen(dto.entityData.getSeventeen() + 1);
                            dto.entityData.setSeventeeni((int) (dto.entityData.getSeventeeni() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 18) {
                            dto.entityData.setEighteen(dto.entityData.getEighteen() + 1);
                            dto.entityData.setEighteeni((int) (dto.entityData.getEighteeni() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 19) {
                            dto.entityData.setNineteen(dto.entityData.getNineteen() + 1);
                            dto.entityData.setNineteeni((int) (dto.entityData.getNineteeni() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 20) {
                            dto.entityData.setTwenty(dto.entityData.getTwenty() + 1);
                            dto.entityData.setTwentyi((int) (dto.entityData.getTwentyi() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 21) {
                            dto.entityData.setTwentyOne(dto.entityData.getTwentyOne() + 1);
                            dto.entityData.setTwentyOnei((int) (dto.entityData.getTwentyOnei() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 22) {
                            dto.entityData.setTwentyTwo(dto.entityData.getTwentyTwo() + 1);
                            dto.entityData.setTwentyTwoi((int) (dto.entityData.getTwentyTwoi() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 23) {
                            dto.entityData.setTwentyThree(dto.entityData.getTwentyThree() + 1);
                            dto.entityData.setTwentyThreei((int) (dto.entityData.getTwentyThreei() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 24) {
                            dto.entityData.setTwentyFour(dto.entityData.getTwentyFour() + 1);
                            dto.entityData.setTwentyFouri((int) (dto.entityData.getTwentyFouri() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 25) {
                            dto.entityData.setTwentyFive(dto.entityData.getTwentyFive() + 1);
                            dto.entityData.setTwentyFivei((int) (dto.entityData.getTwentyFivei() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 26) {
                            dto.entityData.setTwentySix(dto.entityData.getTwentySix() + 1);
                            dto.entityData.setTwentySixi((int) (dto.entityData.getTwentySixi() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 27) {
                            dto.entityData.setTwentySeven(dto.entityData.getTwentySeven() + 1);
                            dto.entityData.setTwentySeveni((int) (dto.entityData.getTwentySeveni() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 28) {
                            dto.entityData.setTwentyEight(dto.entityData.getTwentyEight() + 1);
                            dto.entityData.setTwentyEighti((int) (dto.entityData.getTwentyEighti() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 29) {
                            dto.entityData.setTwentyNine(dto.entityData.getTwentyNine() + 1);
                            dto.entityData.setTwentyNinei((int) (dto.entityData.getTwentyNinei() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 30) {
                            dto.entityData.setThirty(dto.entityData.getThirty() + 1);
                            dto.entityData.setThirtyi((int) (dto.entityData.getThirtyi() + br.getTotalPrice()));
                        }
                        else if (start.getDayOfMonth() == 31) {
                            dto.entityData.setThirtyOne(dto.entityData.getThirtyOne() + 1);
                            dto.entityData.setThirtyOnei((int) (dto.entityData.getThirtyOnei() + br.getTotalPrice()));
                        }
                        else {
                            return null;
                        }
                    }
                    start = start.plusDays(1);
                }
            }
        }
        return dto;
    }


    private GraphicDataYearlyOwnerDto examinateYearlyDataBoatOwner() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BoatOwner boatOwner = boatOwnerRepository.findById(user.getId()).get();
        List<Boat> allBoats = boatRepository.findBoatByBoatOwner(boatOwner);
        int january = 0;
        int february = 0;
        int march = 0;
        int april = 0;
        int may = 0;
        int june = 0;
        int july = 0;
        int august = 0;
        int september = 0;
        int october = 0;
        int november = 0;
        int december = 0;
        int januaryi = 0;
        int februaryi = 0;
        int marchi = 0;
        int aprili = 0;
        int mayi = 0;
        int junei = 0;
        int julyi = 0;
        int augusti = 0;
        int septemberi = 0;
        int octoberi = 0;
        int novemberi = 0;
        int decemberi = 0;
        double incomings = 0;
        GraphicDataYearlyOwnerDto yearly = new GraphicDataYearlyOwnerDto();
        for (Boat b : allBoats) {
            GraphicDataYearlyEntityDto dto = calclulateYearlyDataForEntity(b, findAllReservationsByBoat(b));
            System.out.println(dto);
            if (dto == null) {
                return null;
            }
            january += dto.entityData.getJanuary();
            february += dto.entityData.getFebruary();
            march += dto.entityData.getMarch();
            april += dto.entityData.getApril();
            may += dto.entityData.getMay();
            june += dto.entityData.getJune();
            july += dto.entityData.getJuly();
            august += dto.entityData.getAugust();
            september += dto.entityData.getSeptember();
            october += dto.entityData.getOctober();
            november += dto.entityData.getNovember();
            december += dto.entityData.getDecember();
            incomings += dto.entityData.getIncomings();
            january += dto.entityData.getJanuary();
            february += dto.entityData.getFebruary();
            march += dto.entityData.getMarch();
            april += dto.entityData.getApril();
            may += dto.entityData.getMay();
            june += dto.entityData.getJune();
            july += dto.entityData.getJuly();
            august += dto.entityData.getAugust();
            september += dto.entityData.getSeptember();
            october += dto.entityData.getOctober();
            november += dto.entityData.getNovember();
            december += dto.entityData.getDecember();
            januaryi += dto.entityData.getJanuaryi();
            februaryi += dto.entityData.getFebruaryi();
            marchi += dto.entityData.getMarchi();
            aprili += dto.entityData.getAprili();
            mayi += dto.entityData.getMayi();
            junei += dto.entityData.getJunei();
            julyi += dto.entityData.getJulyi();
            augusti += dto.entityData.getAugusti();
            septemberi += dto.entityData.getSeptemberi();
            octoberi += dto.entityData.getOctoberi();
            novemberi += dto.entityData.getNovemberi();
            decemberi += dto.entityData.getDecemberi();
            yearly.entityData.add(dto);
        }
        yearly.allData.setJanuary(january);
        yearly.allData.setFebruary(february);
        yearly.allData.setMarch(march);
        yearly.allData.setApril(april);
        yearly.allData.setMay(may);
        yearly.allData.setJune(june);
        yearly.allData.setJuly(july);
        yearly.allData.setAugust(august);
        yearly.allData.setSeptember(september);
        yearly.allData.setOctober(october);
        yearly.allData.setNovember(november);
        yearly.allData.setDecember(december);
        yearly.allData.setIncomings(incomings);
        yearly.allData.setJanuaryi(januaryi);
        yearly.allData.setFebruaryi(februaryi);
        yearly.allData.setMarchi(marchi);
        yearly.allData.setAprili(aprili);
        yearly.allData.setMayi(mayi);
        yearly.allData.setJunei(junei);
        yearly.allData.setJulyi(julyi);
        yearly.allData.setAugusti(augusti);
        yearly.allData.setSeptemberi(septemberi);
        yearly.allData.setOctoberi(octoberi);
        yearly.allData.setNovemberi(novemberi);
        yearly.allData.setDecemberi(decemberi);
        yearly.allData.setIncomings(incomings);
        return yearly;
    }

    private GraphicDataYearlyOwnerDto examinateYearlyDataMansionOwner() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MansionOwner mansionOwner = mansionOwnerRepository.findById(user.getId()).get();
        List<Mansion> allBoats = mansionRepository.findAllByMansionOwner(mansionOwner);
        int january = 0;
        int february = 0;
        int march = 0;
        int april = 0;
        int may = 0;
        int june = 0;
        int july = 0;
        int august = 0;
        int september = 0;
        int october = 0;
        int november = 0;
        int december = 0;
        int januaryi = 0;
        int februaryi = 0;
        int marchi = 0;
        int aprili = 0;
        int mayi = 0;
        int junei = 0;
        int julyi = 0;
        int augusti = 0;
        int septemberi = 0;
        int octoberi = 0;
        int novemberi = 0;
        int decemberi = 0;
        double incomings = 0;
        GraphicDataYearlyOwnerDto yearly = new GraphicDataYearlyOwnerDto();
        for (Mansion b : allBoats) {
            GraphicDataYearlyEntityDto dto = calclulateYearlyDataForEntity(b, findAllReservationsByMansion(b));
            System.out.println(dto);
            if (dto == null) {
                return null;
            }
            january += dto.entityData.getJanuary();
            february += dto.entityData.getFebruary();
            march += dto.entityData.getMarch();
            april += dto.entityData.getApril();
            may += dto.entityData.getMay();
            june += dto.entityData.getJune();
            july += dto.entityData.getJuly();
            august += dto.entityData.getAugust();
            september += dto.entityData.getSeptember();
            october += dto.entityData.getOctober();
            november += dto.entityData.getNovember();
            december += dto.entityData.getDecember();
            januaryi += dto.entityData.getJanuaryi();
            februaryi += dto.entityData.getFebruaryi();
            marchi += dto.entityData.getMarchi();
            aprili += dto.entityData.getAprili();
            mayi += dto.entityData.getMayi();
            junei += dto.entityData.getJunei();
            julyi += dto.entityData.getJulyi();
            augusti += dto.entityData.getAugusti();
            septemberi += dto.entityData.getSeptemberi();
            octoberi += dto.entityData.getOctoberi();
            novemberi += dto.entityData.getNovemberi();
            decemberi += dto.entityData.getDecemberi();
            incomings += dto.entityData.getIncomings();
            yearly.entityData.add(dto);
        }
        yearly.allData.setJanuary(january);
        yearly.allData.setFebruary(february);
        yearly.allData.setMarch(march);
        yearly.allData.setApril(april);
        yearly.allData.setMay(may);
        yearly.allData.setJune(june);
        yearly.allData.setJuly(july);
        yearly.allData.setAugust(august);
        yearly.allData.setSeptember(september);
        yearly.allData.setOctober(october);
        yearly.allData.setNovember(november);
        yearly.allData.setDecember(december);

        yearly.allData.setJanuaryi(januaryi);
        yearly.allData.setFebruaryi(februaryi);
        yearly.allData.setMarchi(marchi);
        yearly.allData.setAprili(aprili);
        yearly.allData.setMayi(mayi);
        yearly.allData.setJunei(junei);
        yearly.allData.setJulyi(julyi);
        yearly.allData.setAugusti(augusti);
        yearly.allData.setSeptemberi(septemberi);
        yearly.allData.setOctoberi(octoberi);
        yearly.allData.setNovemberi(novemberi);
        yearly.allData.setDecemberi(decemberi);
        yearly.allData.setIncomings(incomings);


        return yearly;
    }

    private GraphicDataYearlyOwnerDto examinateYearlyDataFishingInstructor() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FishingInstructor fi = fishingInstructorRepository.findById(user.getId()).get();
        List<Adventure> allBoats = adventureRepository.findAllByFishingInstructor(fi);
        int january = 0;
        int february = 0;
        int march = 0;
        int april = 0;
        int may = 0;
        int june = 0;
        int july = 0;
        int august = 0;
        int september = 0;
        int october = 0;
        int november = 0;
        int december = 0;
        int januaryi = 0;
        int februaryi = 0;
        int marchi = 0;
        int aprili = 0;
        int mayi = 0;
        int junei = 0;
        int julyi = 0;
        int augusti = 0;
        int septemberi = 0;
        int octoberi = 0;
        int novemberi = 0;
        int decemberi = 0;
        double incomings = 0;
        GraphicDataYearlyOwnerDto yearly = new GraphicDataYearlyOwnerDto();
        for (Adventure b : allBoats) {
            GraphicDataYearlyEntityDto dto = calclulateYearlyDataForEntity(b, findAllReservationsByAdventure(b));
            System.out.println(dto);
            if (dto == null) {
                return null;
            }
            january += dto.entityData.getJanuary();
            february += dto.entityData.getFebruary();
            march += dto.entityData.getMarch();
            april += dto.entityData.getApril();
            may += dto.entityData.getMay();
            june += dto.entityData.getJune();
            july += dto.entityData.getJuly();
            august += dto.entityData.getAugust();
            september += dto.entityData.getSeptember();
            october += dto.entityData.getOctober();
            november += dto.entityData.getNovember();
            december += dto.entityData.getDecember();
            incomings += dto.entityData.getIncomings();
            january += dto.entityData.getJanuary();
            february += dto.entityData.getFebruary();
            march += dto.entityData.getMarch();
            april += dto.entityData.getApril();
            may += dto.entityData.getMay();
            june += dto.entityData.getJune();
            july += dto.entityData.getJuly();
            august += dto.entityData.getAugust();
            september += dto.entityData.getSeptember();
            october += dto.entityData.getOctober();
            november += dto.entityData.getNovember();
            december += dto.entityData.getDecember();
            januaryi += dto.entityData.getJanuaryi();
            februaryi += dto.entityData.getFebruaryi();
            marchi += dto.entityData.getMarchi();
            aprili += dto.entityData.getAprili();
            mayi += dto.entityData.getMayi();
            junei += dto.entityData.getJunei();
            julyi += dto.entityData.getJulyi();
            augusti += dto.entityData.getAugusti();
            septemberi += dto.entityData.getSeptemberi();
            octoberi += dto.entityData.getOctoberi();
            novemberi += dto.entityData.getNovemberi();
            decemberi += dto.entityData.getDecemberi();
            yearly.entityData.add(dto);
        }
        yearly.allData.setJanuary(january);
        yearly.allData.setFebruary(february);
        yearly.allData.setMarch(march);
        yearly.allData.setApril(april);
        yearly.allData.setMay(may);
        yearly.allData.setJune(june);
        yearly.allData.setJuly(july);
        yearly.allData.setAugust(august);
        yearly.allData.setSeptember(september);
        yearly.allData.setOctober(october);
        yearly.allData.setNovember(november);
        yearly.allData.setDecember(december);
        yearly.allData.setIncomings(incomings);
        yearly.allData.setJanuaryi(januaryi);
        yearly.allData.setFebruaryi(februaryi);
        yearly.allData.setMarchi(marchi);
        yearly.allData.setAprili(aprili);
        yearly.allData.setMayi(mayi);
        yearly.allData.setJunei(junei);
        yearly.allData.setJulyi(julyi);
        yearly.allData.setAugusti(augusti);
        yearly.allData.setSeptemberi(septemberi);
        yearly.allData.setOctoberi(octoberi);
        yearly.allData.setNovemberi(novemberi);
        yearly.allData.setDecemberi(decemberi);
        yearly.allData.setIncomings(incomings);


        return yearly;
    }

    private GraphicDataYearlyEntityDto calclulateYearlyDataForEntity(AbstractEntity b, List<AbstractReservation> regulaReservations) {
        GraphicDataYearlyEntityDto dto = new GraphicDataYearlyEntityDto(b.getId(), b.getName());
        dto.entityData = new GraphicDataYearly();
        for (AbstractReservation br : regulaReservations) {
            if ((br.getStartDate().getYear() + 1900) == LocalDateTime.now().getYear() && br.getStatus() != ReservationStatus.CANCELLED && br.getUser() != null) {
                dto.entityData.setIncomings(dto.entityData.getIncomings() + br.getTotalPrice());
                if (br.getStartDate().getMonth() == Calendar.JANUARY) {
                    dto.entityData.setJanuary(dto.entityData.getJanuary() + 1);
                    dto.entityData.setJanuaryi((int) (dto.entityData.getJanuaryi() + br.getTotalPrice()));
                }
                else if (br.getStartDate().getMonth() == Calendar.FEBRUARY) {
                    dto.entityData.setFebruary(dto.entityData.getFebruary() + 1);
                    dto.entityData.setFebruaryi((int) (dto.entityData.getFebruaryi() + br.getTotalPrice()));
                }
                else if (br.getStartDate().getMonth() == Calendar.MARCH) {
                    dto.entityData.setMarch(dto.entityData.getMarch() + 1);
                    dto.entityData.setMarchi((int) (dto.entityData.getMarchi() + br.getTotalPrice()));
                }
                else if (br.getStartDate().getMonth() == Calendar.APRIL) {
                    dto.entityData.setAprili((int) (dto.entityData.getAprili() + br.getTotalPrice()));
                    dto.entityData.setApril( dto.entityData.getApril() + 1);
                }
                else if (br.getStartDate().getMonth() == Calendar.MAY) {
                    dto.entityData.setMayi((int) (dto.entityData.getMayi() + br.getTotalPrice()));
                    dto.entityData.setMay((dto.entityData.getMayi() + 1));
                }
                else if (br.getStartDate().getMonth() == Calendar.JUNE) {
                    dto.entityData.setJune(dto.entityData.getJune() + 1);
                    dto.entityData.setJunei((int) (dto.entityData.getJunei() + br.getTotalPrice()));
                }
                else if (br.getStartDate().getMonth() == Calendar.JULY) {
                    dto.entityData.setJuly(dto.entityData.getJuly() + 1);
                    dto.entityData.setJulyi((int) (dto.entityData.getJulyi() + br.getTotalPrice()));
                }
                else if (br.getStartDate().getMonth() == Calendar.AUGUST) {
                    dto.entityData.setAugust(dto.entityData.getAugust() + 1);
                    dto.entityData.setAugusti((int) (dto.entityData.getAugusti() + br.getTotalPrice()));
                }
                else if (br.getStartDate().getMonth() == Calendar.SEPTEMBER) {
                    dto.entityData.setSeptember(dto.entityData.getSeptember() + 1);
                    dto.entityData.setSeptemberi((int) (dto.entityData.getSeptemberi() + br.getTotalPrice()));
                }
                else if (br.getStartDate().getMonth() == Calendar.OCTOBER) {
                    dto.entityData.setOctober(dto.entityData.getOctober() + 1);
                    dto.entityData.setOctoberi((int) (dto.entityData.getOctoberi() + br.getTotalPrice()));
                }
                else if (br.getStartDate().getMonth() == Calendar.NOVEMBER) {
                    dto.entityData.setNovember(dto.entityData.getNovember() + 1);
                    dto.entityData.setNovemberi((int) (dto.entityData.getNovemberi() + br.getTotalPrice()));
                }
                else if (br.getStartDate().getMonth() == Calendar.DECEMBER) {
                    dto.entityData.setDecember(dto.entityData.getDecember() + 1);
                    dto.entityData.setDecemberi((int) (dto.entityData.getDecemberi() + br.getTotalPrice()));
                }
                else return null;
            }

        }
        return dto;

    }

    public List<AbstractReservation> findAllReservationsByBoat(Boat b) {
        List<AbstractReservation> regulaReservations = new ArrayList<>(boatReservationRepository.findAllByBoatAndStatusNot(b, ReservationStatus.CANCELLED));
        regulaReservations.addAll(boatDiscountReservationRepository.findAllByBoatAndStatusNot(b, ReservationStatus.CANCELLED));
        return regulaReservations;
    }

    public List<AbstractReservation> findAllReservationsByMansion(Mansion m) {
        List<AbstractReservation> regulaReservations = new ArrayList<>(mansionReservationRepository.findAllByMansionAndStatusNot(m, ReservationStatus.CANCELLED));
        regulaReservations.addAll(mansionDiscountReservationRepository.findAllByMansionAndStatusNot(m, ReservationStatus.CANCELLED));
        return regulaReservations;
    }

    public List<AbstractReservation> findAllReservationsByAdventure(Adventure a) {
        List<AbstractReservation> regulaReservations = new ArrayList<>(adventureReservationRepository.findAllByAdventure(a));
        regulaReservations.addAll(adventureDiscountReservationRepository.findAllByAdventure(a));
        return regulaReservations;
    }


}



