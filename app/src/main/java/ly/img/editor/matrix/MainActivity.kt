package ly.img.editor.matrix

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ly.img.editor.ApparelEditor
import ly.img.editor.EditorDefaults
import ly.img.editor.EngineConfiguration
import ly.img.editor.ShowErrorDialogEvent
import ly.img.editor.matrix.ui.theme.EditorMatrixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EditorMatrixTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val sceneUri = Uri.parse("file:///android_asset/apparel.scene")
                    ApparelEditor(engineConfiguration = EngineConfiguration(
                        license = BuildConfig.LICENSE,
                        onCreate = { engine, eventHandler ->
                            EditorDefaults.onCreate(engine = engine, sceneUri = sceneUri, eventHandler = eventHandler)
                        },
                        onError = { error, _, eventHandler ->
                            Log.d("EditorMatrix", "error", error)
                            eventHandler.send(ShowErrorDialogEvent(error))
                        }
                    )) {
                        finish()
                    }
                }
            }
        }
    }
}
