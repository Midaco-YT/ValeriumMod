package fr.valerium.valemod.utils;

import java.util.Random;

public class StemFixedRandom extends Random {
  private boolean do_proper_random = false;
  
  public int nextInt(int i) {
    if (this.do_proper_random) {
      this.do_proper_random = !this.do_proper_random;
      return super.nextInt(i);
    } 
    this.do_proper_random = !this.do_proper_random;
    return 0;
  }
}
