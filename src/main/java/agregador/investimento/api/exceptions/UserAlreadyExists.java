package agregador.investimento.api.exceptions;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(String messge){
        super(messge);
    }
}
