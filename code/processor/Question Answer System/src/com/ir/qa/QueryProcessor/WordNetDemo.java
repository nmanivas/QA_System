package com.ir.qa.QueryProcessor;// Demo basic WordNet functionalityimport java.io.File;import java.util.*;import net.didion.jwnl.data.*;import net.didion.jwnl.data.relationship.*;import java.util.TreeMap;import org.apache.solr.client.solrj.SolrQuery;import org.apache.solr.client.solrj.SolrServerException;import org.apache.solr.client.solrj.impl.HttpSolrServer;import org.apache.solr.client.solrj.response.QueryResponse;import org.apache.solr.common.SolrDocument;import org.apache.solr.common.SolrDocumentList;import net.didion.jwnl.JWNLException;import net.didion.jwnl.data.IndexWord;import net.didion.jwnl.data.POS;public class WordNetDemo {	public Map<String, String> demoWordNet(Set<String> fieldTypes) throws JWNLException,			SolrServerException {		/*		 * 0 // Initialize the database // You must configure the properties		 * file to point to your dictionary files		 * WordNetHelper.initialize("file_properties.xml");		 * 		 * //id, entity_type, largest_city, area, public_transit, capital,		 * government_type, demonym, established, l //location, employees,		 * headquaters, //formed, jurisdiction, nickname, founded, ground,		 * full_name, manager, league, owner, //chairman, name, born, died,		 * nationality, residence, fields, occupation, education, alma_mater,		 * //known_for, awards, institutions, contributions, spouse, _version_		 * // Morphy result = new Morphy(word); //return result; String[]		 * categoryList={"id","entity_type","largest_city","area",		 * "public_transit", "capital", "government_type", "demonym",		 * "established","location", "employees", "headquaters","formed",		 * "jurisdiction", "nickname", "founded", "ground", "full_name",		 * "manager", "league", "owner", "chairman", "name", "born", "died",		 * "nationality","residence", "fields", "occupation", "education",		 * "alma_mater","known_for", "awards", "institutions", "contributions",		 * "spouse", "_version_"}; // Demo finding parts of speech String x;		 * Map<String,String> Similaritymap=new TreeMap<String,String>();		 * 		 * 		 * if(!Similaritymap.containsKey("biggest city")) {		 * Similaritymap.put("biggest city","largest_city"); }		 * if(!Similaritymap.containsKey("type of government")) {		 * Similaritymap.put("type of government","government_type"); }		 * if(!Similaritymap.containsKey("key city")) {		 * Similaritymap.put("key city","capital"); }		 * if(!Similaritymap.containsKey("key city")) {		 * Similaritymap.put("key city","capital"); }		 * if(!Similaritymap.containsKey("started")) {		 * Similaritymap.put("started","established"); }		 * if(!Similaritymap.containsKey("place")) {		 * Similaritymap.put("place","location"); }		 * if(!Similaritymap.containsKey("job")) { Similaritymap.put("job",		 * "occupation"); } if(!Similaritymap.containsKey("profession")) {		 * Similaritymap.put("profession", "occupation"); }		 * if(!Similaritymap.containsKey("college")) {		 * Similaritymap.put("college", "alma mater"); }		 * if(!Similaritymap.containsKey("birth")) {		 * //System.out.println("Hai"); Similaritymap.put("birth", "born"); }		 * if(!Similaritymap.containsKey("death")) {		 * //System.out.println("Hai"); Similaritymap.put("death", "died"); }		 * if(!Similaritymap.containsKey("wife")) { Similaritymap.put("wife",		 * "spouse"); } if(!Similaritymap.containsKey("husband")) {		 * //System.out.println("Hai"); Similaritymap.put("husband","spouse"); }		 */		// Initialize the database		// You must configure the properties file to point to your dictionary		// files		WordNetHelper.initialize(System.getProperty("server.home")+File.separator+"files"+File.separator+"file_properties.xml");		// id, entity_type, largest_city, area, public_transit, capital,		// government_type, demonym, established, l		// location, employees, headquaters,		// formed, jurisdiction, nickname, founded, ground, full_name, manager,		// league, owner,		// chairman, name, born, died, nationality, residence, fields,		// occupation, education, alma_mater,		// known_for, awards, institutions, contributions, spouse, _version_		// Morphy result = new Morphy(word);//		String[] categoryList = (String[]) feildNames1.toArray();		String[] categoryList=new String[fieldTypes.size()];	    categoryList=fieldTypes.toArray(categoryList);			    String x;		Map<String, String> Similaritymap = new TreeMap<String, String>();		// return result;		// String[] categoryList={"id","entity_type","largest_city","area",		// "public_transit", "capital", "government_type", "demonym",		// "established","location", "employees", "headquaters","formed",		// "jurisdiction", "nickname", "founded", "ground", "full_name",		// "manager", "league", "owner",		// "chairman", "name", "born", "died", "nationality","residence",		// "fields", "occupation", "education", "alma_mater","known_for",		// "awards", "institutions", "contributions", "spouse", "_version_"};		// Demo finding parts of speech		for (int i = 0; i < categoryList.length; i++)		{			if (categoryList[i].contains("_") && !categoryList[i].equalsIgnoreCase("_version_"))			{				String categorySplit[] = categoryList[i].split("_");				if (categorySplit.length > 1)				{					if (categorySplit.length == 2)					{						Similaritymap.put(categorySplit[1] + " "								+ categorySplit[0], categoryList[i]);					}					if (categorySplit.length == 3)					{						Similaritymap.put(categorySplit[1] + " "								+ categorySplit[0] + " " + categorySplit[2],								categoryList[i]);						Similaritymap.put(categorySplit[2] + " "								+ categorySplit[1] + " " + categorySplit[0],								categoryList[i]);						Similaritymap.put(categorySplit[1] + " "								+ categorySplit[2] + " " + categorySplit[0],								categoryList[i]);						Similaritymap.put(categorySplit[2] + " "								+ categorySplit[0] + " " + categorySplit[1],								categoryList[i]);						Similaritymap.put(categorySplit[0] + " "								+ categorySplit[2] + " " + categorySplit[1],								categoryList[i]);					}				}			}		}		if(!Similaritymap.containsKey("partner4"))	      {	   	  Similaritymap.put("partner4","spouse");	      }	        if(!Similaritymap.containsKey("partner"))	      {	   	  Similaritymap.put("partner","spouse");	      }	        if(!Similaritymap.containsKey("siblings4"))	      {	   	  Similaritymap.put("siblings4","relatives");	      }	        if(!Similaritymap.containsKey("siblings"))	      {	   	  Similaritymap.put("siblings","relatives");	      }	        if(!Similaritymap.containsKey("dignified name"))	      {	   	  Similaritymap.put("dignified suffix","honorific_suffix");	      }	        if(!Similaritymap.containsKey("dignified prefix"))	      {	   	  Similaritymap.put("dignfied prefix","honorific_prefix");	      }	        if(!Similaritymap.containsKey("baptized"))	      {	   	  Similaritymap.put("baptized","baptism_date");	       	      }	        if(!Similaritymap.containsKey("corpse discovered2"))	      {	   	  Similaritymap.put("corpse discovered2","body_discovered");	      }	        if(!Similaritymap.containsKey("corpse discovered"))	      {	   	  Similaritymap.put("corpse discovered","body_discovered");	      }	        if(!Similaritymap.containsKey("house2"))	      {	   	  Similaritymap.put("house2","residence");	      }	        if(!Similaritymap.containsKey("earnings3"))	      {	   	  Similaritymap.put("earnings3","salary");	      }	        if(!Similaritymap.containsKey("earnings"))	      {	   	  Similaritymap.put("earnings","salary");	      }	        if(!Similaritymap.containsKey("recognition"))	      {	   	  Similaritymap.put("recognition","awards");	      }	        if(!Similaritymap.containsKey("batting position"))	      {	   	  Similaritymap.put("batting position","position");	      }	        if(!Similaritymap.containsKey("biggest city"))	      {	   	  Similaritymap.put("biggest city","largest_city");	      }	        if(!Similaritymap.containsKey("present club"))	      {	   	  Similaritymap.put("present club","current_club");	      }	        if(!Similaritymap.containsKey("turn proffessional1"))	      {	   	  Similaritymap.put("turn professional1","turned_pro");	      }	        if(!Similaritymap.containsKey("short form"))	      {	   	  Similaritymap.put("short form","abbreviation");	      }	        if(!Similaritymap.containsKey("head office2"))	      {	   	  Similaritymap.put("head office2","headquaters");	      }	        if(!Similaritymap.containsKey("head office"))	      {	   	  Similaritymap.put("head office","headquaters");	      }	        if(!Similaritymap.containsKey("services offerred3"))	      {	   	  Similaritymap.put("services offerred3","services");	      }	        if(!Similaritymap.containsKey("services offerred"))	      {	   	  Similaritymap.put("services offerred","services");	      }	        //if(!Similaritymap.containsKey("number of employees"))	      // {	    //   Similaritymap.put("number of employees", "n");	      //}	        if(!Similaritymap.containsKey("discovered1"))	      {	   	  Similaritymap.put("discovered1", "discovered");	      }	        if(!Similaritymap.containsKey("discovered2"))	      {	   	  Similaritymap.put("discovered2", "discovery_site");	      }	        if(!Similaritymap.containsKey("discovered3"))	      {	   	  Similaritymap.put("discovered3", "discovery_method");	      }	        if(!Similaritymap.containsKey("discovered"))	      {	   	  Similaritymap.put("discovered", "discovery_site");	      }	        if(!Similaritymap.containsKey("head4"))	      {	   	  Similaritymap.put("head4","leader_name");	      }	        if(!Similaritymap.containsKey("head"))	      {	   	  Similaritymap.put("head","leader_name");	      }	        if(!Similaritymap.containsKey("biggest city"))	      {	   	  Similaritymap.put("biggest city","largest_city");	      }	        if(!Similaritymap.containsKey("sister concerns3"))	      {	   	  Similaritymap.put("sister concernes3","subsidiaries");	      }	        if(!Similaritymap.containsKey("sister concerns"))	      {	   	  Similaritymap.put("sister concernes","subsidiaries");	      }	        	        if(!Similaritymap.containsKey("number of staff3"))	      {	   	  Similaritymap.put("number of staff3","num_staff");	      }	        if(!Similaritymap.containsKey("number of staff"))	      {	   	  Similaritymap.put("number of staff","num_staff");	      }	        if(!Similaritymap.containsKey("number of volunteers3"))	      {	   	  Similaritymap.put("number of volunteers3","num_volunteers");	      }	        if(!Similaritymap.containsKey("number of volunteers"))	      {	   	  Similaritymap.put("number of volunteers","num_volunteers");	      }	        	      if(!Similaritymap.containsKey("type of government"))	      {	   	  Similaritymap.put("type of government","government_type");	      }	      if(!Similaritymap.containsKey("total income3"))	      {	   	  Similaritymap.put("total income3","net_income");	      }	      if(!Similaritymap.containsKey("total income"))	      {	   	  Similaritymap.put("total income","net_income");	      }	      if(!Similaritymap.containsKey("manager title"))	      {	   	  Similaritymap.put("manager title","mgr_title");	      }	      if(!Similaritymap.containsKey("present coach"))	      {	   	  Similaritymap.put("present coach","current_coach");	      }	      if(!Similaritymap.containsKey("present coach4"))	      {	   	  Similaritymap.put("present coach4","current_coach");	      }	       	      if(!Similaritymap.containsKey("champions league wins3"))	      {	   	  Similaritymap.put("champions league wins3","clt20_wins");	      }	       	      if(!Similaritymap.containsKey("champions league wins"))	      {	   	  Similaritymap.put("champions league wins","clt20_wins");	      }	       	      if(!Similaritymap.containsKey("latitude"))	      {	   	  Similaritymap.put("latitude","latd");	      }	      if(!Similaritymap.containsKey("area in kilometers"))	      {	   	  Similaritymap.put("area in kilometers","area_km2");	      }	      if(!Similaritymap.containsKey("area in square miles"))	      {	   	  Similaritymap.put("area in square miles","area_sq_mi");	      }	      if(!Similaritymap.containsKey("percentage of water3"))	      {	   	  Similaritymap.put("percentage of water3","percent_water");	      }	      if(!Similaritymap.containsKey("percentage of water"))	      {	   	  Similaritymap.put("percentage of water","percent_water");	      }	      if(!Similaritymap.containsKey("driving side"))	      {	   	  Similaritymap.put("driving side","drives_on");	      }	      if(!Similaritymap.containsKey("originate2"))	      {	   	  Similaritymap.put("originate2","origin");	      }	      if(!Similaritymap.containsKey("elevation in meters"))	      {	   	  Similaritymap.put("elevation in meters","elevation_m");	      }	      if(!Similaritymap.containsKey("elevation in feet"))	      {	   	  Similaritymap.put("elevation in feet","elevation_ft");	      }	      if(!Similaritymap.containsKey("discharge in meters"))	      {	   	  Similaritymap.put("discharge in meters","discharge_m3/s");	      }	      if(!Similaritymap.containsKey("discharge in cubic feet"))	      {	   	  Similaritymap.put("discharge in cubic feet","discharge_cuft/s");	      }	      if(!Similaritymap.containsKey("area of watershed in kilometers"))	      {	   	  Similaritymap.put("area of watershed in kilometers","watershed_km2");	      }	      if(!Similaritymap.containsKey("area of watershed in square miles"))	      {	   	  Similaritymap.put("area of watershed in square miles","watershed_sqmi");	      }	      if(!Similaritymap.containsKey("left tributaries"))	      {	   	  Similaritymap.put("left tributaries","left_tribs");	      }	      if(!Similaritymap.containsKey("left tributaries3"))	      {	   	  Similaritymap.put("left tributaries3","left_tribs");	      }	      if(!Similaritymap.containsKey("right tributaries3"))	      {	   	  Similaritymap.put("right tributaries3","right_tribs");	      }	      if(!Similaritymap.containsKey("right tributaries"))	      {	   	  Similaritymap.put("right tributaries","left_tribs");	      }	       	      if(!Similaritymap.containsKey("number of visitors3"))	      {	   	  Similaritymap.put("number of visitors3","visitors");	      }	      if(!Similaritymap.containsKey("number of visitors"))	      {	   	  Similaritymap.put("number of visitors","visitors");	      }	      if(!Similaritymap.containsKey("key exhibits3"))	      {	   	  Similaritymap.put("key exhibits3","exhibits");	      }	       	      if(!Similaritymap.containsKey("key exhibits"))	      {	   	  Similaritymap.put("key exhibits","exhibits");	      }	      if(!Similaritymap.containsKey("length in kilometers"))	      {	   	  Similaritymap.put("length in kilometers","length_km");	      }	      if(!Similaritymap.containsKey("key city"))	      {	   	  Similaritymap.put("key city","capital");	      }	      if(!Similaritymap.containsKey("started1"))	      {	   	  Similaritymap.put("started1","established");	      }	      if(!Similaritymap.containsKey("place"))	      {	   	  Similaritymap.put("place","location");	      }	      if(!Similaritymap.containsKey("job"))	      {	   	  Similaritymap.put("job", "occupation");	      }	      if(!Similaritymap.containsKey("profession"))	      {	   	  Similaritymap.put("profession", "occupation");	      }	      if(!Similaritymap.containsKey("occupation"))	      {	   	  Similaritymap.put("occupation", "profession");	      }	      if(!Similaritymap.containsKey("college"))	      {	   	  Similaritymap.put("college", "alma mater");	      }	      if(!Similaritymap.containsKey("born1"))	      {	   	  //System.out.println("Hai");	   	  Similaritymap.put("born1", "birth_date");	      }	       	      if(!Similaritymap.containsKey("born2"))	      {	   	  //System.out.println("Hai");	   	  Similaritymap.put("born2", "birth_place");	      }	      if(!Similaritymap.containsKey("born1"))	      {	   	  //System.out.println("Hai");	   	  Similaritymap.put("born1", "birth_date");	      }	      if(!Similaritymap.containsKey("die1"))	      {	   	  //System.out.println("Hai");	   	  Similaritymap.put("die1", "death_date");	      }	      if(!Similaritymap.containsKey("die2"))	      {	   	  //System.out.println("Hai");	   	  Similaritymap.put("die2", "death_place");	      }	      if(!Similaritymap.containsKey("die3"))	      {	   	  //System.out.println("Hai");	   	  Similaritymap.put("die3", "death_cause");	      }	      if(!Similaritymap.containsKey("penalty"))	      {	   	  //System.out.println("Hai");	   	  Similaritymap.put("penalty", "criminal_penalty");	      }	      if(!Similaritymap.containsKey("charge"))	      {	   	  //System.out.println("Hai");	   	  Similaritymap.put("charge", "criminal_charge");	      }	       	       	      if(!Similaritymap.containsKey("disappeared1"))	      {	   	  //System.out.println("Hai");	   	  Similaritymap.put("disappeared1", "disappeared_date");	      }	      if(!Similaritymap.containsKey("disappeared2"))	      {	   	  //System.out.println("Hai");	   	  Similaritymap.put("disappeared2", "disappeared_place");	      }	      if(!Similaritymap.containsKey("disappeared"))	      {	   	  //System.out.println("Hai");	   	  Similaritymap.put("disappeared", "disappeared_place");	      }	      if(!Similaritymap.containsKey("wife3"))	      {	   	  Similaritymap.put("wife3", "spouse");	      }	      if(!Similaritymap.containsKey("husband3"))	      {	   	  Similaritymap.put("husband3", "spouse");	      }	       	      if(!Similaritymap.containsKey("wife4"))	      {	   	  Similaritymap.put("wife4", "spouse");	      }	      if(!Similaritymap.containsKey("husband4"))	      {	   	  //System.out.println("Hai");	   	  Similaritymap.put("husband4","spouse");	      }	      if(!Similaritymap.containsKey("formed1"))	      {	   	  Similaritymap.put("formed1","formed");	      }		for (int i = 0; i < categoryList.length; i++) {			POS[] p = findPartsOfSpeech(categoryList[i].replaceAll("_", " "));			for (int j = 0; j < p.length; j++) {				x = p[j].getLabel();				IndexWord w = WordNetHelper.getWord(POS.getPOSForLabel(x),						categoryList[i].replaceAll("_", " "));			//	ArrayList<String> hyper = findRelatedWordsDemo(w,				//		PointerType.HYPERNYM);			//	ArrayList<String> hypo = findRelatedWordsDemo(w,				//		PointerType.HYPONYM);				ArrayList<String> simi = findRelatedWordsDemo(w,						PointerType.SIMILAR_TO);				ArrayList<String> seeal = findRelatedWordsDemo(w,						PointerType.SEE_ALSO);			/*	if (hyper != null) {					for (int k = 0; k < hyper.size(); k++) {						if (!Similaritymap.containsKey(hyper.get(k).toString()))							Similaritymap.put(hyper.get(k).toString(),									categoryList[i]);					}				}				if (hypo != null) {					for (int k = 0; k < hypo.size(); k++) {						if (!Similaritymap.containsKey(hypo.get(k).toString()))							Similaritymap.put(hypo.get(k).toString(),									categoryList[i]);					}				}*/				if (seeal != null) {					for (int k = 0; k < seeal.size(); k++) {						if (!Similaritymap.containsKey(seeal.get(k).toString()))							Similaritymap.put(seeal.get(k).toString(),									categoryList[i]);					}				}				if (simi != null) {					for (int k = 0; k < simi.size(); k++) {						if (!Similaritymap.containsKey(simi.get(k).toString()))							Similaritymap.put(simi.get(k).toString(),									categoryList[i]);					}				}			}		}		// System.out.println("Hai:"+Similaritymap);		return Similaritymap;		// Similaritymap.put("birth", "born");		// IndexWord w = WordNetHelper.getWord(POS.NOUN, "");		// Similaritymap.put("biggest city", "largest_city");		// Demo finding a list of related words (synonyms)		// System.out.println("Word:"+w.toString());		// findRelatedWordsDemo(w,PointerType.SIMILAR_TO);		// Demo finding a list of related words		// X is Hypernym of Y if every Y is of type X		// Hyponym is the inverse		// IndexWord w = WordNetHelper.getWord(POS.NOUN, "contribution");		// findRelatedWordsDemo(w,PointerType.HYPONYM);		// ArrayList a=findRelatedWordsDemo(w,PointerType.HYPERNYM);		// System.out.println(a);		// Example of Symmetric Relationship (Similar to, i.e. Synonym)		// IndexWord start = WordNetHelper.getWord(POS.ADJECTIVE, "funny");		// ndexWord end = WordNetHelper.getWord(POS.NOUN, "location");		// findRelationshipsDemo(start,end,PointerType.SIMILAR_TO);		// Example of Asymmetric Relationship (HYPERNYM)		// start = WordNetHelper.getWord(POS.NOUN, "bird");		// end = WordNetHelper.getWord(POS.NOUN, "reptile");		// findRelationshipsDemo(start,end,PointerType.HYPERNYM);		// Some other types of relationships		// findRelationshipsDemo(start,end,PointerType.ANTONYM);		// findRelationshipsDemo(start,end,PointerType.ENTAILMENT);		// findRelationshipsDemo(start,end,PointerType.HYPONYM);		// System.out.println("Demonstrating a Tree operation");		// WordNetHelper.showRelatedTree(start,3,PointerType.HYPERNYM);	}	public static POS[] findPartsOfSpeech(String word) throws JWNLException {		// System.out.println("\nFinding parts of speech for " + word + ".");		// Get an array of parts of speech		POS[] pos = WordNetHelper.getPOS(word);		return pos;		// If we found at least one we found the word		/*		 * if (pos.length > 0) { // Loop through and display them all for (int i		 * = 0; i < pos.length; i++) { System.out.println("POS: " +		 * pos[i].getLabel()); } } else { System.out.println("I could not find "		 * + word + " in WordNet!");		 */	}	public static void listGlosses(IndexWord word) throws JWNLException {		System.out.println("\n\nDefinitions for " + word.getLemma() + ":");		// Get an array of Synsets for a word		Synset[] senses = word.getSenses();		// Display all definitions		for (int i = 0; i < senses.length; i++) {			System.out.println(word + ": " + senses[i].getGloss());		}	}	// This function lists related words of type of relation for a given word	public static ArrayList<String> findRelatedWordsDemo(IndexWord w,			PointerType type) throws JWNLException {		// System.out.println("\n\nI am now going to find related words for " +		// w.getLemma() + ", listed in groups by sense.");		// System.out.println("We'll look for relationships of type " +		// type.getLabel() + ".");		// String[] altWords;		// Call a function that returns an ArrayList of related senses		ArrayList a = WordNetHelper.getRelated(w, type);		ArrayList<String> relwords = new ArrayList<String>();		if (a == null) {			// System.out.println("Hmmm, I didn't find any related words.");			return null;		} else {			// return a;			// String[] altWords=new String[]();			// Display the words for all the senses			for (int i = 0; i < a.size(); i++) {				Synset s = (Synset) a.get(i);				Word[] words = s.getWords();				// s.				for (int j = 0; j < words.length; j++) {					// altWords[j]=new String(words[j].getLemma());					relwords.add(words[j].getLemma());					// System.out.println(words[j].getLemma()+",");					// if (j != words.length-1) System.out.print(", ");				}			}// System.out.println();			return relwords;		}	}	public static void findRelationshipsDemo(IndexWord start, IndexWord end,			PointerType type) throws JWNLException {		System.out.println("\n\nTrying to find a relationship between \""				+ start.getLemma() + "\" and \"" + end.getLemma() + "\".");		System.out.println("Looking for relationship of type "				+ type.getLabel() + ".");		// Ask for a Relationship object		Relationship rel = WordNetHelper.getRelationship(start, end, type);		// If it's not null we found the relationship		if (rel != null) {			// Display depth			System.out.println("The depth of this relationship is: "					+ rel.getDepth());			System.out.println("Here is how the words are related: ");			// Get a list of the words that make up the relationship			ArrayList a = WordNetHelper.getRelationshipSenses(rel);			System.out.println("Start: " + start.getLemma());			// Display all senses			for (int i = 0; i < a.size(); i++) {				Synset s = (Synset) a.get(i);				Word[] words = s.getWords();				System.out.print(i + ": ");				for (int j = 0; j < words.length; j++) {					System.out.println(words[j].getLemma());					if (j != words.length - 1)						System.out.print(", ");				}				System.out.println();			}		} else {			System.out					.println("I could not find a relationship between these words!");		}	}}