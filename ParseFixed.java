public class ParseFixed
{
    public static boolean isInt(String str) 
    {
        try 
        {
            Integer.parseInt(str);
            return true;
        } 
        catch (NumberFormatException nfe) 
        {
            return false;
        }
    }
    
    public static boolean isBool(String str)
    {
        if (str == "true" || str == "false")
        {
            return true;
        }
        
        return false;
    }
    
    static boolean parse(String[] arr) 
    {
        if (arr.length == 0) return false;
        
        int ind = 0;
        boolean valid = true;
        String val = arr[ind];
        
        if (val.equals("A")) 
        {
            ind++;
            if (ind < arr.length && isInt(arr[ind])) 
            {
                // valid
            } 
            else 
            {
                valid = false;
            }
        } 
        else if (val.equals("B")) 
        {
            ind++;
            if (ind < arr.length && isInt(arr[ind])) 
            {
                ind++;
                if (ind < arr.length && isBool(arr[ind])) 
                {
                    // valid
                } 
                else 
                {
                    valid = false;
                }
            }
            else
            {
                valid = false;
            }
        } 
        else if (val.equals("C"))// val.equals("C")
        { 
            ind++;
            // is also missing ind < arr.length
            if (ind < arr.length && isBool(arr[ind])) 
            {
                // valid
            } 
            else 
            {
                valid = false;
            }
        }
        
        if (valid && ind == arr.length-1)
        {
            return true;
        }
        
        return false;
        //return valid && ind == arr.length - 1;
  }
  
	public static void main(String[] args) 
	{
		String[] arr = {"B", "5", "true"};
		System.out.println(parse(arr));
	}
}
