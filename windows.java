

public class windows {
	
	private WebDriver driver;
	private String parentHandle, childHandle, urlActual, comilla;	
	
	
	@Before
	public void setUp()throws Exception
	{//le indicamos en donde tenemos el ejecutable, previamente importado al proyecto.
		System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver/chromedriver.exe");
		//instanciamos la variable: 
		driver = new ChromeDriver();
		//llamamos los metodos, propiedades de la variable: 
		driver.manage().window().maximize();
		
		//abrimos la página:
		//los cupones de la promocion indican que esta disponible desde el jun 2022 al jun 2023
		//ver las imagenes adjuntas:
		 driver.get("https://www.hyrtutorials.com/p/window-handles-practice.html");
		//driver.quit();
		
		
	}
	
	
	@After
	public void tearDown()//throws Exception
	{//driver.quit();
		}

	@Test
	public void tested() throws InterruptedException 
		{
		
         driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        
         //guardamos el manejador de la ventana padre en variable en un String:
		 parentHandle = driver.getWindowHandle();
		 
		 System.out.println("ventana principal: "+ parentHandle);
	     driver.findElement(By.id("newTabsBtn")).click();
	     Thread.sleep(5000);
	     //espera implicita:
	     //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	     
	     //espera Explicita: 
	     //WebDriverWait elWait = new WebDriverWait(driver, 10);
	     //elWait.until(ExpectedConditions.titleContains("oyetu"));
	     
	     
		 //almacena todas las ventanas abiertas en el arreglo tipo String llamado handles
	     Set<String>handles = driver.getWindowHandles();
	     System.out.println("imprimiendo los handle:"  + handles);
	
	     
	     //el siguiente ciclo, for each, permite acceder a cada uno de las ventanas
	     //abiertas en el navegador que se asignan en la variable handle.
	     int i= 1;
		 for (String handle: handles) {
			 System.out.println("imprimiendo el handle: " +i +" " + handle);
			 //recorro todos los elementos diferentes al padre: if(!handle.equals(parentHandle)) {
			 if(i==3) {
		     driver.switchTo().window(handle);
			 driver.findElement(By.id("firstName")).sendKeys("mapaputa");
			 driver.findElement(By.id("lastName")).sendKeys("torcua");
			 driver.findElement(By.id("hindichbx")).click();
			 driver.findElement(By.id("email")).sendKeys("mp@mail.com");
			 driver.findElement(By.id("password")).sendKeys("123kid");
			 System.out.println("esta funcionando en: "+i+" "+handle);
			 //driver.findElement(By.id("registerbtn")).click();
			 Thread.sleep(5000);
			 }
			 i++;
			 }
		 
	    driver.switchTo().window(parentHandle);
	    driver.findElement(By.id("name")).sendKeys("el manejador para la ventana es:"+parentHandle);
	    
	    
	    //abriendo la URL actual en otra pestaña:
	    JavascriptExecutor executor = (JavascriptExecutor)driver;
	    urlActual = driver.getCurrentUrl();
	    comilla = "'";
	    System.out.println(urlActual);
	    //se hace necesario "concatenar" un par de comillas asi
        //"window.open('https://www.google.com')";
	    String newTab = "window.open("+comilla+urlActual+comilla+")";
	    executor.executeScript(newTab);
	    Thread.sleep(8000);
	    
	    //nos devolvemos a la ventana padre
	    driver.switchTo().window(parentHandle);
	    driver.findElement(By.id("name")).sendKeys("la url"+urlActual+"abierta en otra ventana");
	    
	    // Enviar la combinación de teclas Ctrl+Y
        
	    
	    //Duplicando la pagina actual conlos mismos permisos:
	    Actions asion = new Actions(driver);
	    //actions.keyDown(Keys.CONTROL).sendKeys("l").keyUp(Keys.CONTROL).perform();
	    asion.keyDown(Keys.CONTROL).sendKeys("l").keyUp(Keys.CONTROL).perform();

        // Enviar la combinación de teclas Alt+Enter
	    asion.keyDown(Keys.ALT).sendKeys(Keys.ENTER).keyUp(Keys.ALT).perform();
       
	    
	    System.out.println("las ventanas actuales:"+driver.getWindowHandles());
	    System.out.println("la ventana actual es:"+driver.getWindowHandle());
//	
	    
	    
		}
	}
