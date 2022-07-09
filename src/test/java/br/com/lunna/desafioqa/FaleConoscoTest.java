package br.com.lunna.desafioqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static org.junit.jupiter.api.Assertions.*;

public class FaleConoscoTest {

    private WebDriver navegador;

    @BeforeEach
    void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        this.navegador = new ChromeDriver(options);
    }

    @AfterEach
    void shutdown() {
        navegador.quit();
    }

    @Test
    void naoDeveriaSubmeterFormularioComDadosNaoPreenchidos() {
        navegador.navigate().to("https://gwcloud.company");

        navegador.findElement(By.partialLinkText("Jamcracker")).click();

        navegador.findElement(By.partialLinkText("CONTATO")).click();

        navegador.findElement(By.id("wf-form-FORM-DE-CONTATO")).submit();

        WebElement mensagem = navegador.findElement(By.className("form01_success"));
        assertEquals("none", mensagem.getCssValue("display"));
    }

    @Test
    void deveriaSubmeterFormularioComDadosPreenchidos() {
        navegador.navigate().to("https://gwcloud.company");

        navegador.findElement(By.partialLinkText("Jamcracker")).click();

        navegador.findElement(By.partialLinkText("CONTATO")).click();

            navegador.findElement(By.id("Nome-2")).sendKeys("Nome Teste");
            navegador.findElement(By.id("Email-6")).sendKeys("email@teste.com.br");
            navegador.findElement(By.id("Assunto-2")).sendKeys("Assunto teste");
            navegador.findElement(By.id("Mensagem-2")).sendKeys("Mensagem Teste");

        navegador.findElement(By.id("wf-form-FORM-DE-CONTATO")).submit();

        WebElement mensagem = navegador.findElement(By.className("form01_success"));

        assertEquals("block", mensagem.getCssValue("display"));
    }


}
