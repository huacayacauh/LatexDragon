class LoadBar {

  constructor (dom) {
    this.dom = dom;
    this.value = 0;
  }

  increment (value) {
    if ((this.value + value) > 100)
      return;

    this.value += value;
    $(this.dom).css ("width", this.value + "%");

    console.log(this.value);
    if (this.value == 100) {
      setTimeout (function() {
        $("#loadbar").hide();
      }, 1500);
    }

  }
}
