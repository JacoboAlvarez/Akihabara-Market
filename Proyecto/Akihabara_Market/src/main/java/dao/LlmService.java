package dao;

import org.json.JSONObject;

import config.ConfigLoader;
import interfaz.Interfaz_Consola;
import model.ProductoOtaku;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LlmService {
	static ConfigLoader cl = new ConfigLoader();
	private static String ObtenerApi(){
		String apu = null;
		try {
			apu = cl.getPropertyAsStatic("api.key");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apu;
	}
	
	private static final String API_KEY = ObtenerApi(); // AQUI VA LA API
	private static final String URL = "https://openrouter.ai/api/v1/chat/completions"; //URL BASE PARA LA IA

	
	public String generarDescripcionProducto(ProductoOtaku producto) throws Throwable {
        String prompt = "Genera una descripción atractiva de no más de 15 palabras para el producto: " + producto.getNombre() + " teniendo en cuenta su categoria de " + producto.getCategoria() +
                        "Destaca sus características principales para los fans del anime y manga, tienes que nombrar el nombre del producto:  " + producto.getNombre() ;
        
        return enviarPregunta(prompt);
    }
	
	
	public String sugerirCategoria(String nombre) throws Exception {
		String prompt = "Sugiereme una categoria para el siguiente producto" + nombre + ", el nombre del producto puede indicarte una categoria, con 3 o 4 palabras "
				+ " la categoria tiene que ser de la siguiente lista: 'Manga','Figura', 'Poster', 'Cartas', 'Llavero', 'Ropa', 'Videojuegos'. Piensa antes de Contestar";
		return enviarPregunta(prompt);
	}
	
	
	public String enviarPregunta(String prompt) throws Exception {
        // Configurar el cuerpo de la solicitud en JSON
        JSONObject requestBody = new JSONObject()
            .put("model", "gpt-3.5-turbo") // Modelo a usar lo hemos sacado de la propia pagina web
            .put("messages", new JSONObject[] {
                new JSONObject()
                    .put("role", "user")
                    .put("content", prompt)
            })
            .put("max_tokens", 40); // Límite de longitud del mensaje

        // Crear la solicitud HTTP
        RequestBody body = RequestBody.create(
            requestBody.toString(),
            MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
            .url(URL)
            .addHeader("Authorization", "Bearer " + API_KEY)
            .post(body)
            .build();

        // Enviar y procesar la respuesta
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Error: " + response.body().string());
            }

            String responseBody = response.body().string();
            return parsearRespuesta(responseBody);
        }
    } //AQUI ACABA ENVIARPREGUTA
	
	 //PARSEAMOS LA PREGUNTA PARA QUE NOS DEVUELVA UNA LINEA MAS COMODA A LA HORA DE PREGUNTAR
	 private static String parsearRespuesta(String jsonResponse) {
	        JSONObject json = new JSONObject(jsonResponse);
	        return json.getJSONArray("choices")
	                   .getJSONObject(0)
	                   .getJSONObject("message")
	                   .getString("content");
	    }
}
