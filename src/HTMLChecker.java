/**
 * 
 */

/**
 * @author Team Bash-Browns
 *
 */
public class HTMLChecker {
	
	private java.util.List<String> htmlStartTags;
	/*= {"<a>", "<b>","<body>", "<button>", "<div>", "<footer>", "<head>","<html>", "<h1>", 
			"<h2>", "<h3>", "<h4>", "<h5>", "<h6>", "<i>", "<li>", "<p>","<title>"};*/
	
	public HTMLChecker(){
		this.htmlStartTags = new java.util.ArrayList<String>();
		
	}
	
	public boolean checkHTML(Buffer b) throws IncorrectHTMLException{
		//checks if every start tag has an end tag
		HTMLTag htmlToCheck = b.getTag();
		for(HTMLTag html : htmlToCheck.getChildren()){
			String startTag = new String(htmlToCheck.getTag());
			if(startTag.charAt(0) != '<'){
				throw new IncorrectHTMLException();
			}
			String endTag = startTag.charAt(0) + "/" + startTag.substring(1);
			if(b.toString().lastIndexOf(endTag) == (b.toString().length() - startTag.length()) - 2){
				throw new IncorrectHTMLException();
			}
			
		}
		return true;
	}
	
	
}
