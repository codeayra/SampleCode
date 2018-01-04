/**
 * 
 */
package com.webwirtue.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author ankitkumar
 *
 */
public class OrderedJobs implements IOrderedJobs{
	
	private List<Character> jobs=new LinkedList<>();


	@Override
	public void registerIndependent(char job) {
		int ascaiiOfLowerA=(int)'a';
		int ascaiiOfLowerZ=(int)'z';
		int ascaiOfJobName=(int)job;
		if(!(ascaiOfJobName>=ascaiiOfLowerA && ascaiOfJobName<=ascaiiOfLowerZ)){
			throw new IllegalArgumentException("Job Name should be a letter");
		}
			if(jobs.contains(job)){
				return;
			}
			jobs.add(job);
	}
	
	

	@Override
	public String getSortedJobNames() {
		// TODO Auto-generated method stub
		StringBuilder result=new StringBuilder();
		for(Character job:jobs){
			result.append(Character.toString(job));
		}
		return result.toString();
	}



	@Override
	public void register(char job, char dependent) {
		//if original exist
		if(jobs.contains(job)){
			int index=jobs.indexOf(job);
			if(index==0){
				((LinkedList<Character>)jobs).addFirst(dependent);
			}else{
				jobs.add(index-1, dependent);
			}
		}else{
			jobs.add(dependent);
			jobs.add(job);
		}
		
	}
	

	
	

}
