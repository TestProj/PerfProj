package common
import java.text.SimpleDateFormat
import java.util.{Date, TimeZone}
import scala.sys.process._
object util{

	def getUtcTimeStamp():String=
	{
		val now = new Date();

		val simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		val timeStamp = simpleDateFormat.format(now);
		return timeStamp;
	}

	def randomString(length:Int, mode:String):String=
	{

		val buffer = new StringBuffer();
		var characters = "";



		if(mode.equals("ALPHA"))
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


		if(mode.equals("ALPHANUMERIC"))
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";


		if(mode.equals("NUMERIC"))
			characters = "1234567890";

		if(mode.equals("ALPHACAP"))
			characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		if(mode.equals("ALPHACAPNUMERIC"))
			characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";


		var charactersLength = characters.length();
		var i=0;

		for (i<- 0 to (length-1) )
		{
			val index = Math.random() * charactersLength;
			buffer.append(characters.charAt(index.toInt));
		}
		return buffer.toString();
	}

	def buildString(scenarioName:String,txname:String,latency:String,HttpStatus:String): String ={
		//Response params
		var Custom = System.getProperty("Custom", "Custom_Param").toString
		var rndnum=randomString(6, "ALPHA")+"_"+randomString(6, "NUMERIC")+"_"+randomString(6, "ALPHACAPNUMERIC")
		var str: String =
			"""
                 {
                     "scenarioName": """"+scenarioName+"""",
                     "transaction": """"+txname+"""",
                     "responseTime": """"+latency+"""",
                     "status": """"+HttpStatus+"""",
                     "run_id": """"+rndnum+"""",
                     "timestamp": """"+getUtcTimeStamp.toString+"""",
                     "custom": {
                           "custom": """"+Custom+"""",
                           "custom1": """"+Custom+""""
                     }
                }
              """
		return str;

	}
}
