/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AST;

/**
 *
 * @author gudja
 */
public class PowerUpCallNode extends ASTNode{
        public String powerup;

    public PowerUpCallNode(String powerup) {
        this.powerup = powerup;
    }

      public String getPowerup() {
        return powerup;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
