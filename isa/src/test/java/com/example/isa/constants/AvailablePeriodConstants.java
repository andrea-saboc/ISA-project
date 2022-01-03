package com.example.isa.constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.example.isa.model.AvailablePeriod;
import com.example.isa.model.Boat;

public class AvailablePeriodConstants {
	
	private AvailablePeriodConstants() {}
	
	public static final Boat BOAT = new Boat("Milicija", "Promo milicije");
	
	private static final AvailablePeriod a1 = new AvailablePeriod(new GregorianCalendar(2022, Calendar.JANUARY, 1).getTime(),new GregorianCalendar(2022, Calendar.JANUARY, 10).getTime(),BOAT);
	private static final AvailablePeriod a2 = new AvailablePeriod(new GregorianCalendar(2022, Calendar.JANUARY, 20).getTime(),new GregorianCalendar(2022, Calendar.JANUARY, 28).getTime(),BOAT);
	private static final AvailablePeriod a3 = new AvailablePeriod(new GregorianCalendar(2022, Calendar.MARCH, 1).getTime(),new GregorianCalendar(2022, Calendar.MARCH, 16).getTime(),BOAT);
	private static final AvailablePeriod a4 = new AvailablePeriod(new GregorianCalendar(2022, Calendar.JUNE, 1).getTime(),new GregorianCalendar(2022, Calendar.JUNE, 21).getTime(),BOAT);


	public static final List<AvailablePeriod> BOAT_AVAILABLE_PERIODS = new ArrayList<AvailablePeriod>() {{
        add(a1);
        add(a2);
        add(a3);
        add(a4);
	}};

	public static final AvailablePeriod TEST_PERIOD_VALID = new AvailablePeriod(new GregorianCalendar(2022, Calendar.JUNE, 2).getTime(),new GregorianCalendar(2022, Calendar.JUNE, 18).getTime(),BOAT);
	public static final AvailablePeriod TEST_PERIOD_NOT_VALID = new AvailablePeriod(new GregorianCalendar(2022, Calendar.DECEMBER, 2).getTime(),new GregorianCalendar(2022, Calendar.DECEMBER, 18).getTime(),BOAT);

}
