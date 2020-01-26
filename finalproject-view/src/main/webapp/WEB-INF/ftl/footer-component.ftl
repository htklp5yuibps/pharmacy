<#assign spring=JspTaglibs["http://www.springframework.org/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign fmt=JspTaglibs["http://java.sun.com/jsp/jstl/fmt"]/>

    <!-- footer -->
    <footer>
      <div class="uui-footer">
        <div class="footer-logo-copyright">
          <div class="epam-logo">
            <@spring.url var="Logo_Epam_Color"
            value="/images/Logo_Epam_Color.svg"/> <img src="${Logo_Epam_Color}"
              alt="" />
          </div>
          <p class="copyright">© 2016 EPAM Systems. All Rights Reserved.</p>
        </div>
        <div class="footer-build">
          <p class="build">
            Build version: <span><@fmt.bundle basename="buildInfo"><@fmt.message key="build-info.version"/></@fmt.bundle></span><br>
            Build date: <span><@fmt.bundle basename="buildInfo"><@fmt.message key="build-info.datetime"/></@fmt.bundle></span><br>
            Developed by: <span><@fmt.bundle basename="buildInfo"><@fmt.message key="build-info.developer"/></@fmt.bundle></span>
          </p>
        </div>
      </div>
    </footer>
    <!-- end of: footer -->