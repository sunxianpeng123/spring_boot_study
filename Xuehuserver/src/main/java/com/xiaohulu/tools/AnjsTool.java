package com.xiaohulu.tools;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.DicLibrary;
import org.ansj.library.StopLibrary;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;

import java.util.HashMap;
import java.util.Map;


public class AnjsTool {
	  private static AnjsTool service;
	  
	   private AnjsTool(){}
	      
	   
	   public static AnjsTool getInstance(){
	           if(null == service){
	            	System.out.println("getInstance start ");
	                service = new AnjsTool();
	                DicLibrary.put(DicLibrary.DEFAULT,DicLibrary.DEFAULT,new Forest());
	            	StopLibrary.put(StopLibrary.DEFAULT,StopLibrary.DEFAULT,new StopRecognition());
	            	System.out.println("getInstance end ");
	           }
	           return service;
	       }
	   
	  
	public void addNewKeyWord(String keyWord){
		DicLibrary.insert(DicLibrary.DEFAULT, keyWord);
	}
	public void addstopKeyWord(String keyWord){
		StopLibrary.insertStopWords(StopLibrary.DEFAULT, keyWord);
	}
	public void deletekeyWord(String keyWord){
		DicLibrary.delete(DicLibrary.DEFAULT, keyWord);

	}
	public Map<String,Integer>  getParse(String data){
		Map<String,Integer> mapresult = new HashMap<String, Integer>();
		Result parse = DicAnalysis.parse(data,DicLibrary.get());
		parse.recognition(StopLibrary.get());
		//System.out.println(parse);
		String getname ="";
		for (Term term : parse) {
			getname =  term.getName();
			if(getname.length()>0){
				mapresult.put(getname,mapresult.containsKey(getname) ? (mapresult.get(getname)  + 1) :  1);
			}
		}

		return mapresult;
	}





	 

}
