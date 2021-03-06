package org.openlrs.liferay.servlet

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import org.openlrs.liferay.servlet.response.GetAboutResponse
import com.google.inject.{Inject, Injector, Singleton}

/**
 * Created by iliyatryapitsin on 25/12/14.
 */

@Singleton
class AboutServlet extends BaseLrsServlet {

  override def doGet(request : HttpServletRequest,
                     response: HttpServletResponse): Unit = jsonAction(() => GetAboutResponse(), request, response)

  override val ServletName: String = "About"
}
