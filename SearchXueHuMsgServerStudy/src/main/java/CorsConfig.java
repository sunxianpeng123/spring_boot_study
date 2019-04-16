import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    /**
     * 最近在做的项目中，我们采用前后端分离式开发。后台RequestController接口写好后，
     * 通过另一台电脑的前端界面用ajax访问我电脑上的后台服务接口时，http请求会返回500的错误。
     * 经过查阅资料得知，这个问题是由“跨域请求”所引起的。那么这个“跨域”到底是什么呢？
     * @return
     */
//    一、什么是跨域请求？
//
//    跨域请求，就是说浏览器在执行脚本文件的ajax请求时，脚本文件所在的服务地址和请求的服务地址不一样。
//    说白了就是ip、网络协议、端口都一样的时候，就是同一个域，否则就是跨域。
//    这是由于Netscape提出一个著名的安全策略——同源策略造成的，这是浏览器对JavaScript施加的安全限制。
//    是防止外网的脚本恶意攻击服务器的一种措施。

//    二、SpringBoot工程如何解决跨域问题？
//
//    那么如何在SpringBoot中处理跨域问题呢？方法有很多，这里着重讲一种——利用@Configuration配置跨域。
//    代码实现如下：
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");// 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*");// 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
        return corsConfiguration;
    }
 
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());// 4 对接口配置跨域设置
        return new CorsFilter(source);

    }



//    “*”代表全部。”**”代表适配所有接口。
//    其中addAllowedOrigin(String origin)方法是追加访问源地址。如果不使用”*”（即允许全部访问源），则可以配置多条访问源来做控制。
}