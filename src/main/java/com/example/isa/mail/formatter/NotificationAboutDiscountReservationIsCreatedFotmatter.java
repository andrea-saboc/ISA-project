package com.example.isa.mail.formatter;

import com.example.isa.model.Client;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.MansionReservation;

import java.text.Format;
import java.text.SimpleDateFormat;

public class NotificationAboutDiscountReservationIsCreatedFotmatter {
    public String getText(DiscountReservation discountReservation, Client client) {

        Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
        String content = "";
        content+= "Hi "+client.getName()+".\n"+".\n";
        content+="It's a great time for a vacation!"+".\n";
        content+="Until "+formatter.format(discountReservation.getValidUntil()) +" we can offer you a "+ discountReservation.calculatePercentageOfDiscount()+"% discount on " +discountReservation.getEntityName()+".\n";
        content+="Pay just " +discountReservation.getPriceWithDiscount()+" euros instead of "+discountReservation.getPriceWithoutDiscount()+".\n";
        content += " Start date of a vacation " + formatter.format(discountReservation.getStartDate()) +".\n";
        content += " End date of a vacation" + formatter.format(discountReservation.getEndDate()) +".\n"+".\n"+".\n";
        content += "Hope you'll enjoy it!";
        return content ;
    }

    public String getSubject(DiscountReservation discountReservation){
        return discountReservation.getType() + " DISCOUNT";
    }
}
