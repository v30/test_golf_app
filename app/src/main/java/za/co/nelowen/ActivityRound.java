package za.co.nelowen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityRound extends AppCompatActivity {
    public static final String HOLE_NUMBER = "hole_number";
    public static final String HOLE_PAR = "hole_par";
    public static final String HOLE_STROKE = "hole_stroke";
    private int holeNumber = 0;
    private EditText etHolePar, etHoleStroke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        etHolePar = this.findViewById(R.id.editTextHolePar);
        etHoleStroke = this.findViewById(R.id.editTextHoleStroke);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        etHolePar.requestFocus();
        etHolePar.setText("");
        etHoleStroke.setText("");
    }

    public void startHole(View view) {
        if (holeNumber < 18) {
            holeNumber++;
            Intent intent = new Intent(this, ActivityHole.class);
            intent.putExtra(HOLE_NUMBER, holeNumber);
            int holePar = 0;
            if (!etHolePar.getText().toString().isEmpty())
                holePar = Integer.parseInt(etHolePar.getText().toString());
            int holeStroke = 0;
            if (!etHoleStroke.getText().toString().isEmpty())
                Integer.parseInt(etHoleStroke.getText().toString());

            System.out.println("[HOLE NUMBER]: " + holeNumber);
            System.out.println("[HOLE PAR]: " + holePar);
            System.out.println("[HOLE STROKE]: " + holeStroke);

            startActivity(intent);
        }
        else
            Toast.makeText(this, "YOU HAVE PLAYED 18 HOLES", Toast.LENGTH_SHORT).show();
    }

    public void endHole(View view) {
        finish();
    }
}
