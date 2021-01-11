import axios from 'axios';

import { Component } from '@angular/core';

import { NavController, NavParams } from 'ionic-angular';

const BACKEND_URL = "http://localhost:8080/";
const CHILDREN_URL = BACKEND_URL + "children";

declare module 'axios' {
  export interface AxiosRequestConfig {
    page?: number;
    pid?: number;
  }
}

interface Parent {
  id: number, sender: string, receiver: string, totalAmount: number, paidAmount:number
}

interface Child {
  id: number, parentId: string, paidAmount:number
}

@Component({
  selector: 'child-table',
  templateUrl: 'child-table.html'
})
export class ChildTablePage {
  parent: Parent = null;
  children: Array<Child> = [];

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.parent = navParams.get('parent');
    console.dir(this.parent);
    this.loadPage();
  }

  async getChildren(pid: number) {
    try {
      const config = {
        params: {
          "pid": pid
        }
      }
      return (await axios.get<Child[]>(CHILDREN_URL, config)).data;
    } catch (error) {
      console.log(error.response); 
      return [];
    }
  }

  async loadPage() {
    this.children = await this.getChildren(this.parent.id);
    console.dir(this.children);
  }
}
