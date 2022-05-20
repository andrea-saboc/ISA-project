package com.example.isa.mail.formatter;
import com.example.isa.model.reservations.Reservation;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class NotificationAboutCreatedReservationFormatter {
    public String getText(Reservation reservation) {

        Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
        String content = "";
        content+= "Hi "+reservation.getUser().getName()+",\n"+"\n";
        content+="It's a great time for a vacation!"+"\n";
        content+="We just created a reservation for you!"+"\n";
        content+="Place: "+reservation.getName()+" "+reservation.getType().toLowerCase(Locale.ROOT)+"."+"\n";
        content += " Start date of a vacation " + formatter.format(reservation.getStartDate()) +"\n";
        content += " End date of a vacation " + formatter.format(reservation.getEndDate()) +"\n"+"\n"+"\n";
        content += "Hope you'll enjoy it!";
        return content;
    }

    public String getSubject(Reservation reservation){
        return "Reservation at "+reservation.getName();
    }
}
