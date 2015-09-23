package pl.polsl.tpdia.fueldata.operators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import pl.polsl.tpdia.fueldata.model.AggregateHolder;
import pl.polsl.tpdia.fueldata.model.Entity;

public class Aggregate {

	final static long ONE_MINUTE_IN_MILLIS = 60000;

	public static <T extends Entity> List<AggregateHolder> applyAvg(
			Class<T> type, List<T> list, Long minutes, String getter) {
		List<AggregateHolder> aggregateHolderList = new ArrayList<>();
		Integer lastIndex = 0;
		Date start = list.get(0).getTimestamp();
		Date end = new Date(start.getTime() + (minutes * ONE_MINUTE_IN_MILLIS));
		while (lastIndex < list.size()) {
			AggregateHolder ah = new AggregateHolder();
			ah.setStart(start);
			ah.setEnd(end);
			ah.setValue(aggregateList(type, list, start, end, getter, lastIndex));
			aggregateHolderList.add(ah);

			if ((lastIndex + 1) >= list.size()) {
				break;
			}
			Date newEnd = list.get(lastIndex + 1).getTimestamp();
			long diff = newEnd.getTime() - end.getTime();
			end = newEnd;
			start = new Date(start.getTime() + diff);
			++lastIndex;
		}
		return aggregateHolderList;
	}

	private static <T extends Entity> Double aggregateList(Class<T> type,
			List<T> list, Date start, Date end, String getter, Integer lastIndex) {
		Double sum = 0.0;
		int counter = 0;
		for (int i = 0; i < list.size(); ++i) {
			T entity = list.get(i);
			if (entity.getTimestamp().before(start)) {
				continue;
			}
			if (entity.getTimestamp().after(end)) {
				break;
			} else {
				lastIndex = i;
			}
			Method m;
			try {
				m = type.getDeclaredMethod(getter);
				sum += (Double) m.invoke(entity);
				++counter;
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		if (counter != 0) {
			return sum / counter;
		}
		return 0.0;
	}
}
