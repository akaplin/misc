import groovy.io.FileType

import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Renames old screenshots with bad names to good ones
 *
 * @author akaplin
 */
class OldScreenshotsRename {
    static void main(String[] args) {
        def path = args ? args[0] : "d:\\Screenshots\\"
        String regex = ".*(\\d{6})_(\\d{6}).*"
        Pattern pattern = Pattern.compile(regex)
        new File(path).eachFileRecurse(FileType.FILES) { file ->
            Matcher m = pattern.matcher(file.getName())
            if (m.find()) {
                def oldDate = new SimpleDateFormat("MMddyy").parse(m.group(1))
                def time = m.group(2)
                file.renameTo("${path}WoWScrnShot_${oldDate.format("YYYY-MM-dd")}_${time}.jpg")
            }
        }
    }
}
