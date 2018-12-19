class Tabs {
  constructor(tabs, tabsContent) {
    this.tabs = tabs;
    this.tabsContent = tabsContent;
    this.itemsTabs = [].slice.call(document.querySelectorAll(`.${this.tabs}`));
    this.itemsContents = [].slice.call(document.querySelectorAll(`.${this.tabsContent}`));

    this.initTabs();
  }

  initTabs() {
    this.itemsTabs.forEach((item, i) => {
      item.addEventListener('click', (event) => {
        event.preventDefault();
        if (!item.classList.contains(`${this.tabs}--active`)) {
          this.itemsTabs.forEach((tab) => {
            tab.classList.remove(`${this.tabs}--active`);
          });
          this.itemsTabs[i].classList.add(`${this.tabs}--active`);

          this.itemsContents.forEach((content) => {
            content.classList.remove(`${this.tabsContent}--active`);
          });
          this.itemsContents[i].classList.add(`${this.tabsContent}--active`);
        }
      });
    });
  }
}
export default Tabs;
