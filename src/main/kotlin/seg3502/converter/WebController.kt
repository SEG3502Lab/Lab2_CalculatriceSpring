package seg3502.converter

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class WebController {

    @ModelAttribute
    fun addAttributes(model: Model) {
        model.addAttribute("error", "")
        model.addAttribute("input1", "")
        model.addAttribute("input2", "")
        model.addAttribute("result", "")
    }

    @RequestMapping("/")
    fun home(): String {
        return "home"
    }

    @GetMapping(value = ["/convert"])
    fun doConvert(
        @RequestParam(value = "input1", required = false) input1: String,
        @RequestParam(value = "input2", required = false) input2: String,
        @RequestParam(value = "operation", required = false) operation: String,
        model: Model
    ): String {

        var input1Val: Double
        var input2Val: Double

        when (operation) {

            "+" ->
                try {
                    input1Val = input1.toDouble()
                    input2Val = input2.toDouble()

                    model.addAttribute("input1", input1)
                    model.addAttribute("input2", input2)
                    model.addAttribute("result", input1Val + input2Val)

                } catch (exp: NumberFormatException) {
                    model.addAttribute("error", "InputFormatError")
                    model.addAttribute("input1", input1)
                    model.addAttribute("input2", input2)
                    model.addAttribute("result", "")
                }

            "-" ->
                try {
                    input1Val = input1.toDouble()
                    input2Val = input2.toDouble()

                    model.addAttribute("input1", input1)
                    model.addAttribute("input2", input2)
                    model.addAttribute("result", input1Val - input2Val)

                } catch (exp: NumberFormatException) {
                    model.addAttribute("error", "InputFormatError")
                    model.addAttribute("input1", input1)
                    model.addAttribute("input2", input2)
                    model.addAttribute("result", "")
                }

            "*" ->
                try {
                    input1Val = input1.toDouble()
                    input2Val = input2.toDouble()

                    model.addAttribute("input1", input1)
                    model.addAttribute("input2", input2)
                    model.addAttribute("result", input1Val * input2Val)

                } catch (exp: NumberFormatException) {
                    model.addAttribute("error", "InputFormatError")
                    model.addAttribute("input1", input1)
                    model.addAttribute("input2", input2)
                    model.addAttribute("result", "")
                }

            "/" ->
                try {
                    input1Val = input1.toDouble()
                    input2Val = input2.toDouble()

                    model.addAttribute("input1", input1)
                    model.addAttribute("input2", input2)

                    if (input2Val == 0.0) {
                        model.addAttribute("error", "DivisionByZeroError")
                        model.addAttribute("result", "")
                    } else {
                        model.addAttribute("result", input1Val / input2Val)
                    }

                } catch (exp: NumberFormatException) {
                    model.addAttribute("error", "InputFormatError")
                    model.addAttribute("input1", input1)
                    model.addAttribute("input2", input2)
                    model.addAttribute("result", "")
                }

            else -> {
                model.addAttribute("error", "OperationFormatError")
                model.addAttribute("input1", input1)
                model.addAttribute("input2", input2)
                model.addAttribute("result", "")
            }
        }

        return "home"
    }
}