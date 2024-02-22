package fr.upjv.agendasportive.controller;

import fr.upjv.agendasportive.models.LoginRequest;
import fr.upjv.agendasportive.models.Utilisateur;
import fr.upjv.agendasportive.repositories.UtilisateurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/p")
public class LoginController {
    // Field Injection is not recommended so I didn't use Autowired
    //@Autowired // Inject dependency into beans
    //private UtilisateurRepository utilisateurRepository;
    private final UtilisateurRepository utilisateurRepository;

    public LoginController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Utilisateur user = (Utilisateur) utilisateurRepository.findByNom(loginRequest.getUsername());
        if (user != null && user.getMdp().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}

/*
public void loginUser(String username, String password) {
    OkHttpClient client = new OkHttpClient();

    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}");
    Request request = new Request.Builder()
            .url("YOUR_BACKEND_URL/api/login")
            .post(body)
            .addHeader("Content-Type", "application/json")
            .build();

    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                // Login successful, handle response
            } else {
                // Login failed, handle error response
            }
        }
    });
}
 */