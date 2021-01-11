import axios from 'axios';

import { Component } from '@angular/core';

import { NavController, NavParams } from 'ionic-angular';

import { ChildTablePage } from '../child-table/child-table';

const BACKEND_URL = "http://localhost:8080/";
const PARENTS_URL = BACKEND_URL + "parent";
const PAGECOUNT_URL = BACKEND_URL + "page-count";

declare module 'axios' {
  export interface AxiosRequestConfig {
    page?: number;
    pid?: number;
  }
}

interface Parent {
  id: number, sender: string, receiver: string, totalAmount: number, paidAmount:number
}

@Component({
  selector: 'parent-table',
  templateUrl: 'parent-table.html'
})
export class ParentTablePage {
  pageCount: number;
  pageNumber: number = 0;
  parents: Parent[] = [];
  parentPages: { [key: number]: Parent[] } = [];

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.loadPageCount().then(() => this.loadPage())
  }

  async loadPageCount() {
    try {
      this.pageCount = (await axios.get<number>(PAGECOUNT_URL)).data;
    } catch (error) {
      console.log(error.response); 
    }
  }

  async getPage(pageNumber: number) {
    try {
      const config = {
        params: {
          "page": pageNumber
        }
      }
      const result = (await axios.get<Parent[]>(PARENTS_URL, config)).data;
      while(result[result.length - 1] === null) {
        result.pop();
      }
      return result;
    } catch (error) {
      console.log(error.response); 
      return [];
    }
  }

  async loadPage() {
    if (!(this.pageNumber in this.parentPages)) {
      this.parents = await this.getPage(this.pageNumber);
      console.log(this.parents);
      this.parentPages[this.pageNumber] = this.parents;
    } else {
      this.parents = this.parentPages[this.pageNumber];
    }
  }

  async loadNextPage() {
    this.pageNumber += 1;
    this.loadPage();
  }

  async loadPreviousPage() {
    this.pageNumber -= 1;
    this.loadPage();
  }

  itemTapped(event, item) {
    this.navCtrl.push(ChildTablePage, {
      parent: item
    });
  }
}
