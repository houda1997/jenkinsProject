import com.example.exception.NoSquareException;
import com.example.model.Matrix;
import com.example.service.MatrixMathematics;
import org.junit.Assert;

public class MatrixMathematicsTest {

    double[][] mat = {{1,2,3},{4,5,6},{7,8,10}};
    double[][] inv = {{1,2},{3,4}};
    double[][] detFail={{1,2},{3,4},{5,6}};

    Matrix matrix = new Matrix(mat);
    Matrix invMat = new Matrix(inv);

    double[][] trons = {{1,4,7},{2,5,8},{3,6,10}};
    double[][] inverse = {{-2,1},{1.5,-0.5}};
    double[][] cofactor = {{2,2,-3},{4,-11,6},{-3,6,-3}};

    @org.junit.Test
    public void transpose() {

        Matrix mTrons = new Matrix(trons);
        for(int i=0;i<trons.length;i++){
            for(int j = 0;j<trons[i].length;j++){
                Assert.assertEquals(mTrons.getValueAt(i,j), MatrixMathematics.transpose(matrix).getValueAt(i,j),0.1);
            }

        }
    }

    @org.junit.Test
    public void inverse() throws NoSquareException {

        Matrix mInver = new Matrix(inverse);
        for(int i=0;i<inverse.length;i++){
            for (int j =0;j<inverse[i].length;j++){
                Assert.assertEquals(mInver.getValueAt(i,j),MatrixMathematics.inverse(invMat).getValueAt(i,j),0.1);
            }
        }

    }

    @org.junit.Test
    public void determinant() throws NoSquareException {
        Assert.assertEquals(-3,MatrixMathematics.determinant(matrix),0.1);

    }


    @org.junit.Test
    public void cofactor() throws NoSquareException {
        Matrix mCof= new Matrix(cofactor);
        for(int i=0;i<cofactor.length;i++){
            for (int j =0;j<cofactor[i].length;j++){
                Assert.assertEquals(mCof.getValueAt(i,j),MatrixMathematics.cofactor(matrix).getValueAt(i,j),0.1);
            }
        }
    }


}