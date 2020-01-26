package view.configuration;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class StandaloneMvcTestViewResolver
		extends InternalResourceViewResolver {
	
	@Override
	protected AbstractUrlBasedView buildView(String viewName)
			throws Exception {
		
		final InternalResourceView view = (InternalResourceView)
				super.buildView(viewName);
    view.setPreventDispatchLoop(false);
    return view;
	}

}
