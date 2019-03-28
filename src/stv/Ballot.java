package stv;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Ballot {
	private Queue<Option> mOptions;
	
	public Ballot(Option... options) {
		mOptions = sort(options);
	}
	
	public void move() {
		mOptions.poll();
	}

	private Queue<Option> sort(Option... unsorted) {
		List<Option> tmp = new ArrayList<>();
		int optionsLength = unsorted[0].getLastPlace();

		initList(tmp, optionsLength);

		for(Option option : unsorted){
			int place = option.getPlace()-1;

			tmp.remove(place);
			tmp.add(place, option);
		}
		return new ArrayDeque<>(tmp);
	}

	private void initList(List<Option> options, int size) {
		for(int i = 0; i < size; ++i) {
			options.add(i, null);
		}
	}

	public Option getFirst() {
		return mOptions.isEmpty() ? null : mOptions.peek();
	}

	public String toString() {
		AtomicReference<String> options = new AtomicReference<>("");

		for(Option option :mOptions) {
			options.set(options.get() + option.getOption() + ",");
		}
		if(options.get().contains(","))
			options.set(options.get().substring(0, options.get().length()-1));

		return options.get();
	}
}
