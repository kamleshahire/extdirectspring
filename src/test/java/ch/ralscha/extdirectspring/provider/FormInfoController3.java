/**
 * Copyright 2010-2013 Ralph Schaer <ralphschaer@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.ralscha.extdirectspring.provider;

import java.math.BigDecimal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectFormPostResult;

@Controller
public class FormInfoController3 {

	@ExtDirectMethod(value = ExtDirectMethodType.FORM_POST_JSON)
	@RequestMapping(value = "/updateInfoJson", method = RequestMethod.POST)
	public ExtDirectFormPostResult updateInfoJson(Locale locale, HttpServletRequest request,
			HttpServletResponse response, @Valid FormInfo formInfo) {

		return new ExtDirectFormPostResult(true);
	}

	@ExtDirectMethod(value = ExtDirectMethodType.FORM_POST_JSON)
	public ExtDirectFormPostResult updateInfoJsonDirect(Locale locale, @Valid FormInfo formInfo) {

		ExtDirectFormPostResult e = new ExtDirectFormPostResult();
		e.addResultProperty("name", formInfo.getName().toUpperCase());
		e.addResultProperty("age", formInfo.getAge() + 10);
		e.addResultProperty("admin", !formInfo.isAdmin());
		BigDecimal bd = new BigDecimal("1000");
		bd = bd.add(formInfo.getSalary());
		e.addResultProperty("salary", bd);
		e.addResultProperty("result", formInfo.getResult() + "RESULT");
		return e;
	}

	@ExtDirectMethod(value = ExtDirectMethodType.FORM_POST_JSON)
	public ExtDirectFormPostResult updateInfoJsonDirectError(Locale locale, HttpServletRequest request,
			HttpServletResponse response, @Valid FormInfo formInfo) {

		ExtDirectFormPostResult e = new ExtDirectFormPostResult();
		e.addError("age", "age is wrong");
		return e;
	}

	@ExtDirectMethod(value = ExtDirectMethodType.FORM_POST_JSON)
	public void updateInfoJsonDirectNotRegistered(Locale locale, HttpServletRequest request,
			HttpServletResponse response, @Valid FormInfo formInfo, BindingResult result) {
	}
}
