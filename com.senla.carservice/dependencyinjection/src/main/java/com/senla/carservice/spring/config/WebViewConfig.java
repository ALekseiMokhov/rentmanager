package com.senla.carservice.spring.config;

/*
@Configuration
@EnableWebMvc
@Profile("ui")
@ComponentScan("com.senla.carservice")*/
public class WebViewConfig /*implements WebMvcConfigurer*/ {
  /*  private final ApplicationContext applicationContext;

    @Autowired
    public WebViewConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean

    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext( applicationContext );
        templateResolver.setPrefix( "classpath:/templates/" );
        templateResolver.setTemplateMode( "HTML5" );
        templateResolver.setSuffix( ".html" );
        return templateResolver;
    }


    @Bean

    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver( templateResolver() );
        templateEngine.setEnableSpringELCompiler( true );
        return templateEngine;
    }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine( templateEngine() );
        registry.viewResolver( resolver );
    }*/
}
